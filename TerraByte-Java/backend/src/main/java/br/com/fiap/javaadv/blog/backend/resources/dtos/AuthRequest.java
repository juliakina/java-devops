package br.com.fiap.javaadv.blog.backend.resources.dtos;

import lombok.Builder;

@Builder()
public record AuthRequest(String email, String password) {

}