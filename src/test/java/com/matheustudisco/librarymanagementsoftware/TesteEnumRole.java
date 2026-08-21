package com.matheustudisco.librarymanagementsoftware;

import com.matheustudisco.librarymanagementsoftware.enums.Role;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TesteEnumRole {
    @Test
    public void testarRole() {
        Role role = null;
    boolean roleBoolean = false;
    List<Role> roleList = new ArrayList<>(List.of(Role.values()));
    int i = 1;
        while(!roleBoolean)

    {
        System.out.println("---Lista de cargos---");
        for (Role roleFor : roleList) {
            System.out.println(i + " - " + roleFor);
            i++;
        }
        System.out.print("Escolha uma das opções: ");
        byte escolha = Byte.parseByte("3");
        if (escolha == 1) {
            role = Role.CLIENTE;
            roleBoolean = true;
        } else if (escolha == 2) {
            role = Role.GERENTE;
            roleBoolean = true;
        } else if (escolha == 3) {
            role = Role.ADMINISTRADOR;
            roleBoolean = true;
        } else {
            System.out.println("Escolha apenas as opções existentes.");
        }
        System.out.println(role);
    }
}
}
