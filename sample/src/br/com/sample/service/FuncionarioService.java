package br.com.sample.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.entity.Funcionario;

@Repository
@Transactional
public class FuncionarioService extends BaseService<Long, Funcionario> {

}