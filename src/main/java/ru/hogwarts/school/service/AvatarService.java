package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final String avatarDir;
    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository, @Value("${avatars.dir}")
            String avatarDir){
        this.avatarRepository=avatarRepository;
        this.studentRepository=studentRepository;
        this.avatarDir=avatarDir;
    }
    public void upload(long studentId, MultipartFile file) throws IOException {
        var student = studentRepository
                .findById(studentId)
                .orElseThrow(StudentNotFoundException::new);

        var path = Path.of(avatarDir);
        if (!path.toFile().exists()){
            Files.createDirectories(path);
        }
        var dotIndex = file.getOriginalFilename().lastIndexOf(".");
        var ext = file.getOriginalFilename().substring(dotIndex + 1);
        var filePath = avatarDir + "/" + student.getId() + "_"  + student.getName() + "." + ext;

        try (var in = file.getInputStream();
             var out = new FileOutputStream(filePath)){
            in.transferTo(out);
        }

        var avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setStudent(student);
        avatar.setFilePath(filePath);
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        avatarRepository.save(avatar);
    }
    public  Avatar findAvatar(long studentId){
        return avatarRepository.findByStudentId(studentId).orElse(null);
    }
    public List<Avatar> getAvatarPage(int pageNumber,int pageSize){
        var request = PageRequest.of(pageNumber-1,pageSize);
        return avatarRepository.findAll(request).getContent();
    }
}
