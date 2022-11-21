package com.kelaskoding.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import com.kelaskoding.entity.Produk;
import com.kelaskoding.repo.ProdukRepo;
import com.kelaskoding.utility.CSVHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("produkService")
@Transactional
public class ProdukService {

    @Autowired
    private ProdukRepo produkRepo;

    public List<Produk> save(MultipartFile file){
        try{
            List<Produk> produk2 = CSVHelper.csvToBooks(file.getInputStream());
            return produkRepo.saveAll(produk2);
        }catch(IOException e){
            throw new RuntimeException("fail to store csv data: "+ e.getMessage());
        }
    }

    public List<Produk> findAll(){
        return produkRepo.findAll();
    }
    
}