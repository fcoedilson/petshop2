package br.com.petshop.cadastro.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import br.com.petshop.architecture.util.Digest;
import br.com.petshop.base.entity.Usuario;

public class UsuarioUtil {
	
	public static Usuario gerarSenhaUsuario(Usuario usuario){
		String senha = usuario.getSenha() + new DateTime().getMillisOfSecond();
		senha = senha.substring(15, senha.length() - 6);
		String senhaMd5 = Digest.MD5digest(senha);
		usuario.setSenha(senhaMd5);
		usuario.setSenhaEmail(senha);
		return usuario;
	}
	
	public static Boolean validarSenhaSeteCaracteres(String senha){
		if(senha.length() < 7){
			return false;
		}
		return true;
	}
	
	public static Boolean validarLetrasNumeros(String senha){
		Integer countCaractere = 0;
		Integer countNumerico = 0;
		for(int x = 0; x < senha.length(); x++){
			if(StringUtils.isNumeric(String.valueOf(senha.charAt(x)))){
				countNumerico++;
			} else {
				countCaractere++;
			}
		}
		
		if(countCaractere == 0 || countNumerico == 0){
			return false;
		}
		return true;
	}
	
	public static Boolean validarSenhasIguais(String senhaNova, String senhaNovaConfirma){
		return senhaNova.equals(senhaNovaConfirma);
	}
}