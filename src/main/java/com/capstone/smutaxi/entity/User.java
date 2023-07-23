package com.capstone.smutaxi.entity;


import com.capstone.smutaxi.enums.Gender;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity // 이 클래스는 데이터베이스의 테이블과 1:1매핑되는 객체다라고 명시 ORM (Object Relation Mapping)
@Getter @Setter
@Table(name="users") //table 이름이 user가 될 수 없음 그래서 네임 따로 지정해준다
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User implements UserDetails {

    @Id
    @Column(name = "user_id")
    private String email;

    private String password;

    private String name;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user")
    private List<ChatParticipant> chatParticipantList = new ArrayList<>();

    //역할기반제어를 위한 roles필드 (Spring Security)
    @ElementCollection(fetch = FetchType.EAGER) //roles 컬렉션
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    //Spring Security Method
    @Override   //사용자의 권한 목록 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    //Spring Security Method
    @Override
    public String getUsername() {
        return email;
    }

    //Spring Security Method
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Spring Security Method
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Spring Security Method
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Spring Security Method
    @Override
    public boolean isEnabled() {
        return true;
    }




}
