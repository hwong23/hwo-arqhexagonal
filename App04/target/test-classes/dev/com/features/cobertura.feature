@addCoberturaToPrima
Feature: Agregar cobertura a la prima
  Debo poder agregar la cobertura a una prima existente.

  Scenario: Agregar cobertura a una prima existente
    Given que tengo una prima ID y el detalle de una cobertura
    When encuentro la prima
    And el valor de la cobertura es válido y este no existe ya
    And El CIDR es válido
    Then Agregar la cobertura a la prima
    