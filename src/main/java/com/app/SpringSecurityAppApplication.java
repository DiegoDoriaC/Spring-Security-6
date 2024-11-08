package com.app;

import com.app.persintence.entity.PermissionEntity;
import com.app.persintence.entity.RoleEnum;
import com.app.persintence.entity.RolesEntity;
import com.app.persintence.entity.UserEntity;
import com.app.persintence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			//Crear los permisos
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();
			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();
			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();
			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();
			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();

			//Creacion de los roles
			RolesEntity roleAdmin = RolesEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();
			RolesEntity roleUser = RolesEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();
			RolesEntity roleInvited = RolesEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();
			RolesEntity roleDeveloper = RolesEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			//Creacion de los usuarios
			UserEntity userSantiago = UserEntity.builder()
					.username("santiago")
					.password("$2a$10$asvqGyczaA8ytBnu55mOAeTf9E74rQ2oEaxtKGnPnzyQhwzwpxgmK")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();
			UserEntity userDiego = UserEntity.builder()
					.username("diego")
					.password("$2a$10$asvqGyczaA8ytBnu55mOAeTf9E74rQ2oEaxtKGnPnzyQhwzwpxgmK")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();
			UserEntity userJavier = UserEntity.builder()
					.username("javier")
					.password("$2a$10$asvqGyczaA8ytBnu55mOAeTf9E74rQ2oEaxtKGnPnzyQhwzwpxgmK")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();
			UserEntity userHugo = UserEntity.builder()
					.username("hugo")
					.password("$2a$10$asvqGyczaA8ytBnu55mOAeTf9E74rQ2oEaxtKGnPnzyQhwzwpxgmK")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userSantiago, userDiego, userJavier, userHugo));
		};
	}

}
