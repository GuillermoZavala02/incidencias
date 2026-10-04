package com.example.incidencias.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.incidencias.entidad.Usuario;
import com.example.incidencias.exepcion.UsuarioNoEncontradoException;
import com.example.incidencias.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio {

   private final UsuarioRepositorio usuarioRepositorio;

   public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
      this.usuarioRepositorio = usuarioRepositorio;
   }

   public List<Usuario> ObtenerTodosLosUsuarios() {
      return usuarioRepositorio.findAll();
   }

   public Usuario ObtenerUsuarioPorId(Long id) {
      return usuarioRepositorio.findById(id).orElseThrow(() -> new UsuarioNoEncontradoException(id));
   }

   public Usuario ObtenerUsuarioPorCorreo(String correo) {
      return usuarioRepositorio.findByCorreo(correo).orElse(null);
   }

   public Usuario GuardarUsuario(Usuario usuario) {
      return usuarioRepositorio.save(usuario);
   }

   public void EliminarUsuario(Long id) {
      usuarioRepositorio.deleteById(id);
   }
}
