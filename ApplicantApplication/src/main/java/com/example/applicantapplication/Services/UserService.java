package com.example.applicantapplication.Services;

import com.example.applicantapplication.Entities.File;
import com.example.applicantapplication.Entities.User;
import com.example.applicantapplication.Repository.FileRepository;
import com.example.applicantapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {

    User createUser(User user, List<byte[]> fileDataList, List<String> fileNames);
    //List<File> createAndSaveFiles(List<byte[]> fileDataList, List<String> fileNames, User user);
}
