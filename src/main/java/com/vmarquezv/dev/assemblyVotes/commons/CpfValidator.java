package com.vmarquezv.dev.assemblyVotes.commons;

import com.vmarquezv.dev.assemblyVotes.commons.status.annotation.Cpf;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String>{
	
	private final int[] PESO_CPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		
		String cpfNumbers = cpf.replaceAll("\\D", "");
		
		if ((cpfNumbers == null) || (cpfNumbers.length() != 11) || cpfNumbers.equals("00000000000")
				|| cpfNumbers.equals("11111111111") || cpfNumbers.equals("22222222222")
				|| cpfNumbers.equals("33333333333") || cpfNumbers.equals("44444444444")
				|| cpfNumbers.equals("55555555555") || cpfNumbers.equals("66666666666")
				|| cpfNumbers.equals("77777777777") || cpfNumbers.equals("88888888888")
				|| cpfNumbers.equals("99999999999")) {
			return false;
		}
		
		Integer number1 = calculateNumbers(cpfNumbers.substring(0, 9), PESO_CPF);
		Integer number2 = calculateNumbers(cpfNumbers.substring(0, 9) + number1, PESO_CPF);
		
		return cpfNumbers.equals(cpfNumbers.substring(0, 9) + number1.toString() + number2.toString());
	}
	
	private int calculateNumbers(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

}