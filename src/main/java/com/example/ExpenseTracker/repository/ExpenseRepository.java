package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // JpaRepository gives us: save(), findAll(), findById(), deleteById()
    // No extra code needed!
}