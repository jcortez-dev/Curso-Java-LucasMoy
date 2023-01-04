package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;


    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario (@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucasmoy@gmail.com");
        usuario.setTelefono("121231");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios (){
        return usuarioDao.getUsuarios();
    }


    @RequestMapping(value = "api/editar")
    public Usuario editar (){
        Usuario usuario = new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucasmoy@gmail.com");
        usuario.setTelefono("121231");
        return usuario;
    }
    @RequestMapping(value = "api/eliminar/{id}", method = RequestMethod.DELETE)
    public void eliminar (@PathVariable Long id){
        usuarioDao.eliminar(id);
    }
    @RequestMapping(value = "api/buscar")
    public Usuario buscar (){
        Usuario usuario = new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucasmoy@gmail.com");
        usuario.setTelefono("121231");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario (@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());

        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

}
