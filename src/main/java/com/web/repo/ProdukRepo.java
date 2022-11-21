package com.kelaskoding.repo;

import com.kelaskoding.entity.Produk;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdukRepo extends JpaRepository<Produk, Long> {
    
}

