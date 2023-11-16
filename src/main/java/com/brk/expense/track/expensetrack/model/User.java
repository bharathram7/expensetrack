package com.brk.expense.track.expensetrack.model;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    // other fields, getters, setters
}
