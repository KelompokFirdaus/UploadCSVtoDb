package com.kelaskoding.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.kelaskoding.entity.Produk;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {

    private static final String TYPE= "text/csv";

    public static boolean hasCSVFormat(MultipartFile file){
        if(!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    public static List<Produk> csvToBooks(InputStream is){
        try{
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser parser = new CSVParser(fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<Produk> produk2 = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = parser.getRecords();

            for(CSVRecord csvRecord: csvRecords){
                Produk produk = new Produk();
                produk.setId(Long.parseLong(csvRecord.get("Id")));
                produk.setName(csvRecord.get("Name"));
                produk.setPrice(Double.parseDouble(csvRecord.get("Price")));
                produk2.add(produk);
            }
            parser.close();
            return produk2;
        }catch(IOException ex){
            throw new RuntimeException("fail to parse CSV file: "+ ex.getMessage());
        }
    }
    
}