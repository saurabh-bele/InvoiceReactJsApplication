package com.example.invoiceapp.controller;

import com.example.invoiceapp.entities.Invoice;
import com.example.invoiceapp.repo.InvoiceRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceRepo invoiceRepo;
    @GetMapping("/get-all-invoices")
    public List<Invoice> getInvoices(@RequestParam(defaultValue = "0") int offset, @RequestParam (defaultValue = "10") int limit)
    {
       Page<Invoice> page = invoiceRepo.findAll(PageRequest.of(offset/limit, limit));
       return page.getContent();
    }
    @PostMapping("/create-invoices")
    public Invoice createInvoices(@RequestBody Invoice invoice)
    {
        return invoiceRepo.save(invoice);
    }
    @PutMapping("/update-invoices")
    public Invoice updateInvoices(@RequestBody Invoice invoice)
    {
        Optional<Invoice> oldInvoice = invoiceRepo.findByInvoiceNumber(invoice.getInvoiceNumber());
        if(oldInvoice.isPresent())
        {
            return invoiceRepo.save(invoice);
        }
        else
        {
            System.out.println("Invoice Not Found");
            return null;
        }
    }

    @DeleteMapping("/delete-invoices/{invoiceNumber}")
    public String deleteInvoices (@PathVariable String invoiceNumber)
    {
        Optional<Invoice> oldInvoice = invoiceRepo.findByInvoiceNumber(invoiceNumber);
        if(oldInvoice.isPresent())
        {
            invoiceRepo.delete(oldInvoice.get());
            return "Invoice successfully deleted";
        }
        else
        {
            return "Invoice Not Found";
        }
    }
}
