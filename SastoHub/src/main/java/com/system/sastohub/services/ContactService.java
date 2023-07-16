package com.system.sastohub.services;



import com.system.sastohub.entity.Contact;
import com.system.sastohub.pojo.ContactPojo;

import java.util.List;

public interface ContactService {
    String save(ContactPojo contactPojo);

    List<Contact> fetchAll();

    void deleteById(Integer id);
}
