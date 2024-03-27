package com.example.applicantapplication.Services;

import com.example.applicantapplication.Entities.File;
import com.example.applicantapplication.Entities.User;
import com.example.applicantapplication.Repository.FileRepository;
import com.example.applicantapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    public UserServiceImpl(UserRepository userRepository, FileRepository fileRepository) {
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }

    public User createUser(User user, List<byte[]> fileDataList, List<String> fileNames) {
        // Check if the number of files and file names match
        if (fileDataList.size() != 3 || fileNames.size() != 3) {
            throw new IllegalArgumentException("Exactly three file data and file names are required.");
        }

        // Create and save file entities
        List<File> files = createAndSaveFiles(user, fileDataList, fileNames);

        // Associate files with the user and save user
        user.setFiles(files);

        return userRepository.save(user);
    }

    private List<File> createAndSaveFiles(User user, List<byte[]> fileDataList, List<String> fileNames) {
        List<File> files = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            File fileEntity = new File();
            fileEntity.setData(fileDataList.get(i));
            fileEntity.setFileName(fileNames.get(i));
            // Set the user id for each file entity
            fileEntity.setUser(user);
            files.add(fileEntity);
        }
        // Save all files together
        return fileRepository.saveAll(files);
    }

}
