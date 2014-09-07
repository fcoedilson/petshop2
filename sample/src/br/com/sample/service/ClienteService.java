package br.com.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Cliente;
import br.com.sample.entity.Usuario;
import br.com.sample.util.BeanUtil;

@Repository
@Transactional
public class ClienteService extends BaseService<Long, Cliente> {

	@Transactional(readOnly = true)
	public List<Cliente> retrieveAll() {

		Usuario user = BeanUtil.usuarioLogado();

		if (BeanUtil.isAdmin(user)) {
			return super.retrieveAll();
		} else {
			List<Cliente> result = new ArrayList<Cliente>();
			return result;
		}
	}

}