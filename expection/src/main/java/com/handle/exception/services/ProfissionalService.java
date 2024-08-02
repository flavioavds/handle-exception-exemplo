// package com.handle.exception.services;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import org.springframework.stereotype.Service;
//
// import com.handle.exception.entity.Loja;
// import com.handle.exception.entity.Profissional;
// import com.handle.exception.exception.constants.ErrorMessages;
// import com.handle.exception.interfaces.LojaRepository;
// import com.handle.exception.interfaces.ProfissionalRepository;
// import
// com.handle.exception.validation.profissional.ProfissionalCargoValidator;
// import com.handle.exception.validation.profissional.ProfissionalValidator;
//
// import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
// import br.com.cassol.cas_ms_exception.exception.errors.CustomException;
// import br.com.cassol.cas_ms_exception.interfaces.Validator;
//
// @Service
// public class ProfissionalService {
//
// private final ProfissionalRepository profissionalRepository;
// private final LojaRepository lojaRepository;
// private Validator<Profissional> validatorChain;
//
// public ProfissionalService(ProfissionalRepository profissionalRepository,
// LojaRepository lojaRepository) {
// this.profissionalRepository = profissionalRepository;
// this.lojaRepository = lojaRepository;
// this.initializeValidators();
// }
//
// private void initializeValidators() {
// Validator<Profissional> profissionalNameValidator = new
// ProfissionalValidator(this.profissionalRepository);
// Validator<Profissional> profissionalCargoValidator = new
// ProfissionalCargoValidator(
// this.profissionalRepository);
// profissionalNameValidator.linkWith(profissionalCargoValidator);
// this.validatorChain = profissionalNameValidator;
// }
//
// public Profissional saveProfissional(Profissional profissional, Long lojaId)
// {
// List<CustomError> errors = new ArrayList<>();
//
// // Verificar a existência da Loja e adicionar erro se necessário
// Loja loja = this.lojaRepository.findById(lojaId).orElse(null);
// if (loja == null) {
// errors.add(new CustomError("loja", ErrorMessages.LOJA_ERROR + lojaId));
// } else {
// // Se a Loja existe, associe-a ao Profissional
// profissional.setLoja(loja);
// }
//
// // Realizar a validação do Profissional
// this.validatorChain.validate(profissional, errors);
//
// // Verificar se há erros e lançar uma exceção se houver
// if (!errors.isEmpty()) {
// throw new CustomException(errors);
// }
//
// // Salvar o Profissional se não houver erros
// return this.profissionalRepository.save(profissional);
// }
// }
