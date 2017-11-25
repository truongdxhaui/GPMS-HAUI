package com.truongdx.config;

import java.util.HashSet;

import com.truongdx.domain.*;
import com.truongdx.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    LeaderRepository leaderRepository;

    StudentRepository studentRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //Create ROLE
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
        if (roleRepository.findByName("ROLE_LECTURER") == null) {
            roleRepository.save(new Role("ROLE_LECTURER"));
        }
        if (roleRepository.findByName("ROLE_LEADER") == null) {
            roleRepository.save(new Role("ROLE_LEADER"));
        }
        if (roleRepository.findByName("ROLE_GUEST") == null) {
            roleRepository.save(new Role("ROLE_GUEST"));
        }

        //Create Faculty
        if (facultyRepository.findByFacultyId("CNTT") == null) {
            facultyRepository.save(new Faculty("CNTT", "Công nghệ thông tin", 0));
        }

        //Create Faculty
        if (facultyRepository.findByFacultyId("KT") == null) {
            facultyRepository.save(new Faculty("KT", "Kế toán", 0));
        }

        //Create lecture
        if (userRepository.findByUsername("gv1") == null) {
            User user = new User();
            user.setUsername("gv1");
            user.setPassword(passwordEncoder.encode("1"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_LECTURER"));
            user.setRoles(roles);
            userRepository.save(user);


            Lecturer lecturer = new Lecturer();
            lecturer.setUserId(userRepository.findByUsername("gv1").getId());
            lecturer.setFirstName("Nguyen Van ");
            lecturer.setLastName("A ");
            lecturer.setDegree("T.S");
            int facultyId = facultyRepository.findByFacultyId("KT").getId();
            if (!(facultyId < 0))
                lecturer.setFacultyId(facultyId);
            lecturerRepository.save(lecturer);

        }

        if (userRepository.findByUsername("gv2") == null) {
            User user = new User();
            user.setUsername("gv2");
            user.setPassword(passwordEncoder.encode("1"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_LECTURER"));
            user.setRoles(roles);
            userRepository.save(user);


            Lecturer lecturer = new Lecturer();
            lecturer.setUserId(userRepository.findByUsername("gv2").getId());
            lecturer.setFirstName("Nguyen Van ");
            lecturer.setLastName("B ");
            lecturer.setDegree("Th.S");
            int facultyId = facultyRepository.findByFacultyId("CNTT").getId();
            if (!(facultyId < 0))
                lecturer.setFacultyId(facultyId);
            lecturerRepository.save(lecturer);

        }

        if (userRepository.findByUsername("sv") == null) {
            User user = new User();
            user.setUsername("sv");
            user.setPassword(passwordEncoder.encode("1"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_STUDENT"));
            user.setRoles(roles);
            userRepository.save(user);


            Student student = new Student();
            student.setUserId(userRepository.findByUsername("sv").getId());
            student.setFirstName("Nguyen Van ");
            student.setLastName("B ");

            int facultyId = facultyRepository.findByFacultyId("CNTT").getId();
            studentRepository.save(student);
        }


    }
}
