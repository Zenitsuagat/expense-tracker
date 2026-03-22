package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseRepository repo;

    // Show all expenses + empty add form
    @GetMapping("/")
    public String listExpenses(Model model) {
        model.addAttribute("expenses", repo.findAll());
        model.addAttribute("newExpense", new Expense());
        return "expenses"; // loads expenses.html
    }

    // Save new expense
    @PostMapping("/add")
    public String addExpense(@ModelAttribute Expense expense) {
        repo.save(expense);
        return "redirect:/";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Expense expense = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        model.addAttribute("expense", expense);
        return "edit"; // loads edit.html
    }

    // Update expense
    @PostMapping("/update/{id}")
    public String updateExpense(@PathVariable Long id,
                                @ModelAttribute Expense expense) {
        expense.setId(id);
        repo.save(expense);
        return "redirect:/";
    }

    // Delete expense
    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}