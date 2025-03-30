Feature: Kullanici olarak otel rezervasyonu oluşturabilirim

  Scenario: Kullanıcı bir otel rezervasyonu oluşturabilir ve rezervasyonu silebilir
    Given Kullanici yeni bir rezervasyon oluşturuyor.
    And Kullanıcı rezervasyon için gereken bilgileri veriyor.
    When Kullanici otel rezervasyonu yaratıyor
    Then Rezervasyon başarılı şekilde oluşturuldu
    And Kullanıcı rezervazyonu iptal ediyor



