package com.hashin.project.service;

import com.hashin.project.bean.AdhaarUserBean;

public interface UserEnrollmentService
{
  
    // adhaar service
    
    public AdhaarUserBean getUserById(String adhaarID);
}
