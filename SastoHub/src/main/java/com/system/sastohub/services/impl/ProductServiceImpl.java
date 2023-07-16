package com.system.sastohub.services.impl;

import com.system.sastohub.entity.Product;
import com.system.sastohub.pojo.ProductPojo;
import com.system.sastohub.repo.ProductRepo;
import com.system.sastohub.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/sastohubimages";


    @Override
    public ProductPojo saveProduct(ProductPojo productpojo) throws IOException {
        Product product = new Product();
        if (productpojo.getPid()!= null){
            product.setProductId(productpojo.getPid());
        }
        product.setProductTitle(productpojo.getPtitle());
        product.setProductCategory(productpojo.getPCategories());
        product.setProductDescription(productpojo.getPDesc());
        product.setProductPrice(productpojo.getPPrice());
        product.setBrandName(productpojo.getBName());
        product.setSize(productpojo.getSize());

        if(productpojo.getImage()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, productpojo.getImage().getOriginalFilename());
            fileNames.append(productpojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, productpojo.getImage().getBytes());

            product.setImage(productpojo.getImage().getOriginalFilename());
        }
        productRepo.save(product);
        return new ProductPojo(product);
    }

    @Override
    public Product fetchById(Integer id) {
        Product product= productRepo.findById(id).orElseThrow(()-> new RuntimeException("Couldnot find"));
        product = Product.builder()
                .productId(product.getProductId())
                .imageBase64(getImageBase64(product.getImage()))
                .productTitle(product.getProductTitle())
                .productCategory(product.getProductCategory())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .brandName(product.getBrandName())
                .size(product.getSize())
                .build();
        return product;
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/sastohubimages/";
        File file = new File(filePath + fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public List<Product> fetchAll() {
        return findAllInList(productRepo.findAll());
    }

    public List<Product> findAllInList(List<Product> list){
        Stream<Product> allCart=list.stream().map(product ->
                Product.builder()
                        .productId(product.getProductId())
                        .imageBase64(getImageBase64(product.getImage()))
                        .productTitle(product.getProductTitle())
//                        .quantity(product.getQuantity())
//                        .price(product.getPrice())
                        .build()
        );

        list = allCart.toList();
        return list;
    }

    @Override
    public void deleteById(Integer id) {
        productRepo.deleteById(id);
    }

}
