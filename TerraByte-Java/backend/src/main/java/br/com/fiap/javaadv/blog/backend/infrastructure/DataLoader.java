package br.com.fiap.javaadv.blog.backend.infrastructure;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.Month;
import java.util.Set;
import java.util.UUID;

@Configuration
public class DataLoader {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDataTutPet(
            UsuarioRepository usuarioRep,
            TipoSoloRepository tipoSoloRep,
            PlantioRepository plantioRep,
            EnderecoPlantioRepository enderecoPlantioRep,
            DefensivoRepository defensivoRep,
            AnalisePlantioRepository analisePlantioRep
    ) {
        return args -> {
            Usuario user1 = usuarioRep.save(Usuario.builder()
                            .id(UUID.fromString("978c415d-7c8b-4b37-af9a-d54fcb1bda46"))
                            .nome("Sandra Nascimento")
                            .dataNascimento(Date.valueOf("1968-11-14"))
                            .telefone("11945414013")
                            .sexo(SexoEnum.F)
                            .email("sandra@gmail.com")
                            .senha(passwordEncoder.encode("sandraReg123"))
                            .urlImg("https://exemplo.com/sandra.png")
                            .build());

            Usuario user2 = usuarioRep.save(Usuario.builder()
                    .id(UUID.fromString("8e9b28e7-fd43-4ee5-b293-74b45730215b"))
                    .nome("Renato gonçalves")
                    .dataNascimento(Date.valueOf("1980-09-04"))
                    .telefone("11972632948")
                    .sexo(SexoEnum.M)
                    .email("renato@gmail.com")
                    .senha(passwordEncoder.encode("renatoGon123"))
                    .urlImg("https://exemplo.com/renato.png")
                    .build());

            Usuario user3 = usuarioRep.save(Usuario.builder()
                    .id(UUID.fromString("2cefea85-1198-43b1-96a9-695168f0f37f"))
                    .nome("Joao Pereira")
                    .dataNascimento(Date.valueOf("1990-09-22"))
                    .telefone("11977772222")
                    .sexo(SexoEnum.M)
                    .email("joao@gmail.com")
                    .senha(passwordEncoder.encode("123456"))
                    .build());

            Usuario user4 = usuarioRep.save(Usuario.builder()
                    .id(UUID.fromString("db2ee926-07f3-4125-bfe6-f3556fe8df8e"))
                    .nome("Fernanda Lima")
                    .dataNascimento(Date.valueOf("1998-03-03"))
                    .telefone("11966663333")
                    .sexo(SexoEnum.F)
                    .email("fernanda@gmail.com")
                    .senha(passwordEncoder.encode("123456"))
                    .build());

            Usuario user5 = usuarioRep.save(Usuario.builder()
                    .id(UUID.fromString("79e941cc-d43b-487c-a131-22c3a97de2c9"))
                    .nome("Ricardo Alves")
                    .dataNascimento(Date.valueOf("1987-07-17"))
                    .telefone("11955554444")
                    .sexo(SexoEnum.M)
                    .email("ricardo@gmail.com")
                    .senha(passwordEncoder.encode("123456"))
                    .build());

            Usuario user6 = usuarioRep.save(Usuario.builder()
                    .id(UUID.fromString("5c93e437-113e-4e01-9b60-32bc31815e32"))
                    .nome("Patricia Gomes")
                    .dataNascimento(Date.valueOf("1992-12-28"))
                    .telefone("11944445555")
                    .sexo(SexoEnum.F)
                    .email("patricia@gmail.com")
                    .senha(passwordEncoder.encode("123456"))
                    .build());

            Usuario user7 = usuarioRep.save(Usuario.builder()
                    .id(UUID.fromString("b53569bf-2745-4e40-b6d4-2be4ebaef1d6"))
                    .nome("Lucas Martins")
                    .dataNascimento(Date.valueOf("1999-01-09"))
                    .telefone("11933336666")
                    .sexo(SexoEnum.M)
                    .email("lucas@gmail.com")
                    .senha(passwordEncoder.encode("123456"))
                    .build());

            Usuario user8 = usuarioRep.save(Usuario.builder()
                    .id(UUID.fromString("74d98a8f-cd56-488e-a28f-fc4de329b1f4"))
                    .nome("Camila Rocha")
                    .dataNascimento(Date.valueOf("1996-06-14"))
                    .telefone("11922227777")
                    .sexo(SexoEnum.F)
                    .email("camila@gmail.com")
                    .senha(passwordEncoder.encode("123456"))
                    .build());

            Usuario user9 = usuarioRep.save(Usuario.builder()
                    .id(UUID.fromString("301f4c31-5a30-46a2-b16a-7b73e7bab7f5"))
                    .nome("Felipe Santos")
                    .dataNascimento(Date.valueOf("1985-11-30"))
                    .telefone("11911118888")
                    .sexo(SexoEnum.M)
                    .email("felipe@gmail.com")
                    .senha(passwordEncoder.encode("123456"))
                    .build());

            Usuario user10 = usuarioRep.save(Usuario.builder()
                    .id(UUID.fromString("0bcf558f-e115-4c81-8bb0-4dd61e614120"))
                    .nome("Amanda Costa")
                    .dataNascimento(Date.valueOf("1993-08-05"))
                    .telefone("11900009999")
                    .sexo(SexoEnum.F)
                    .email("amanda@gmail.com")
                    .senha(passwordEncoder.encode("123456"))
                    .build());



            TipoSolo tp1 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("9387c57c-28ec-404b-b505-fbcf86426812"))
                    .nome("AREIA")
                    .build());

            TipoSolo tp2 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("e3164763-d080-4496-8769-34e8628b2f52"))
                    .nome("AREIA_FRANCA")
                    .build());

            TipoSolo tp3 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("f0bf1027-a4ff-481f-8f99-76ad54f62734"))
                    .nome("FRANCO_ARENOSO")
                    .build());

            TipoSolo tp4 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("0022136f-bacf-45d5-837a-a5d0cf7b7394"))
                    .nome("FRANCA")
                    .build());

            TipoSolo tp5 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("2f4bd064-76ac-49c2-9d43-9cd944dec612"))
                    .nome("FRANCO_SILTOSA")
                    .build());

            TipoSolo tp6 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("b3351c2a-2d08-4b63-8d4c-f2961d1f7c9e"))
                    .nome("SILTE")
                    .build());

            TipoSolo tp7 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("abdeafd2-8204-47d1-8ca8-013e47e14cbc"))
                    .nome("FRANCO_ARGILO_ARENOSA")
                    .build());

            TipoSolo tp8 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("b8246fd2-2910-4ac0-b358-8970967eb9b3"))
                    .nome("FRANCO_ARGILOSA")
                    .build());

            TipoSolo tp9 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("a30d31d1-ad34-4ccb-9b50-65f141e913f2"))
                    .nome("FRANCO_ARGILO_SILTOSA")
                    .build());

            TipoSolo tp10 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("92c2e329-9347-4ec7-9694-9dced263ee2f"))
                    .nome("ARGILO_ARENOSA")
                    .build());

            TipoSolo tp11 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("19ecfccf-79b2-4fae-8f9c-c7cd1a0684c4"))
                    .nome("ARGILA")
                    .build());

            TipoSolo tp12 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("327f9f42-5403-4749-9fb5-e30d4fce120b"))
                    .nome("ARGILO_SILTOSA")
                    .build());

            TipoSolo tp13 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("94ee033b-0d04-4b59-84f8-689b0f221af3"))
                    .nome("MUITO_ARGILOSA")
                    .build());

            TipoSolo tp14 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("da20088c-dab3-42f6-8b43-6121141a99c8"))
                    .nome("DESCONHECIDO")
                    .build());

            EnderecoPlantio end1 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("064d3218-e73f-4110-90f4-b5d9dc724635"))
                    .nome("Fazenda Boa Esperanca")
                    .cep("78000000")
                    .logradouro("Fazenda Boa Esperanca")
                    .cidade("Sorriso")
                    .estado("Mato Grosso")
                    .bairro("Zona Rural")
                    .latitude(-12.542300)
                    .longitude(-55.721100)
                    .argila(10)
                    .areia(80)
                    .silto(10)
                    .raioSoloKm(5)
                    .tipoSolo(tp1)
                    .usuario(user1)
                    .build());

            EnderecoPlantio end2 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("75cf458a-d881-4baf-8e42-6db6bc7ad4ee"))
                    .nome("Sitio Primavera")
                    .cep("75900000")
                    .logradouro("Sitio Primavera")
                    .cidade("Rio Verde")
                    .estado("Goias")
                    .bairro("Interior")
                    .latitude(-17.792300)
                    .longitude(-50.919200)
                    .argila(20)
                    .areia(70)
                    .silto(10)
                    .raioSoloKm(7)
                    .tipoSolo(tp2)
                    .usuario(user1)
                    .build());

            EnderecoPlantio end3 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("53eb4e99-2f50-4b99-ae27-a2c4d30f53a0"))
                    .nome("Fazenda Santa Rita")
                    .cep("38400000")
                    .logradouro("Fazenda Santa Rita")
                    .cidade("Uberlandia")
                    .estado("Minas Gerais")
                    .bairro("Regiao Rural")
                    .latitude(-18.918600)
                    .longitude(-48.277200)
                    .argila(25)
                    .areia(55)
                    .silto(20)
                    .raioSoloKm(9)
                    .tipoSolo(tp3)
                    .usuario(user2)
                    .build());

            EnderecoPlantio end4 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("14cb16f7-a8ff-48b7-87ab-0eaf35709076"))
                    .nome("Chacara Sao Jorge")
                    .cep("14000000")
                    .logradouro("Chacara Sao Jorge")
                    .cidade("Ribeirao Preto")
                    .estado("Sao Paulo")
                    .bairro("Zona Leste")
                    .latitude(-21.177500)
                    .longitude(-47.810300)
                    .argila(30)
                    .areia(40)
                    .silto(30)
                    .raioSoloKm(12)
                    .tipoSolo(tp4)
                    .usuario(user2)
                    .build());

            EnderecoPlantio end5 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("22f7fe9e-e8bd-4770-99c9-d0807998f244"))
                    .nome("Fazenda Horizonte")
                    .cep("85800000")
                    .logradouro("Fazenda Horizonte")
                    .cidade("Cascavel")
                    .estado("Parana")
                    .bairro("Area Agricola")
                    .latitude(-24.955500)
                    .longitude(-53.455200)
                    .argila(20)
                    .areia(20)
                    .silto(60)
                    .raioSoloKm(8)
                    .tipoSolo(tp5)
                    .usuario(user3)
                    .build());

            EnderecoPlantio end6 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("0a1a55d9-07bc-4bd5-8fa9-2d40f4c2745c"))
                    .nome("Sitio Bela Vista")
                    .cep("89800000")
                    .logradouro("Sitio Bela Vista")
                    .cidade("Chapeco")
                    .estado("Santa Catarina")
                    .bairro("Zona Rural")
                    .latitude(-27.100400)
                    .longitude(-52.615200)
                    .argila(15)
                    .areia(10)
                    .silto(75)
                    .raioSoloKm(6)
                    .tipoSolo(tp6)
                    .usuario(user3)
                    .build());

            EnderecoPlantio end7 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("62cf6e4f-0094-445f-a60b-63c6c1f0107b"))
                    .nome("Fazenda Nordeste")
                    .cep("65000000")
                    .logradouro("Fazenda Nordeste")
                    .cidade("Balsas")
                    .estado("Maranhao")
                    .bairro("Interior")
                    .latitude(-7.532100)
                    .longitude(-46.035800)
                    .argila(45)
                    .areia(35)
                    .silto(20)
                    .raioSoloKm(11)
                    .tipoSolo(tp7)
                    .usuario(user4)
                    .build());

            EnderecoPlantio end8 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("20a5aef8-ee96-4132-8890-d9b785befc71"))
                    .nome("Fazenda Campo Verde")
                    .cep("77000000")
                    .logradouro("Fazenda Campo Verde")
                    .cidade("Palmas")
                    .estado("Tocantins")
                    .bairro("Area Aberta")
                    .latitude(-10.184000)
                    .longitude(-48.333600)
                    .argila(60)
                    .areia(20)
                    .silto(20)
                    .raioSoloKm(10)
                    .tipoSolo(tp8)
                    .usuario(user4)
                    .build());

            EnderecoPlantio end9 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("6c53d935-a88e-4142-a54d-c30475fed8b4"))
                    .nome("Sitio Sao Pedro")
                    .cep("56300000")
                    .logradouro("Sitio Sao Pedro")
                    .cidade("Petrolina")
                    .estado("Pernambuco")
                    .bairro("Vale Irrigado")
                    .latitude(-9.389100)
                    .longitude(-40.503000)
                    .argila(50)
                    .areia(15)
                    .silto(35)
                    .raioSoloKm(13)
                    .tipoSolo(tp9)
                    .usuario(user5)
                    .build());

            EnderecoPlantio end10 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("906d51b5-88fb-4907-a971-54aa3227cad9"))
                    .nome("Fazenda Amazonas")
                    .cep("69900000")
                    .logradouro("Fazenda Amazonas")
                    .cidade("Rio Branco")
                    .estado("Acre")
                    .bairro("Floresta")
                    .latitude(-9.974700)
                    .longitude(-67.824300)
                    .argila(70)
                    .areia(20)
                    .silto(10)
                    .raioSoloKm(9)
                    .tipoSolo(tp10)
                    .usuario(user5)
                    .build());

            EnderecoPlantio end11 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("d2975ed0-f738-4a1e-9055-2a385f4b31e3"))
                    .nome("Fazenda Vale Verde")
                    .cep("12000000")
                    .logradouro("Fazenda Vale Verde")
                    .cidade("Campinas")
                    .estado("Sao Paulo")
                    .bairro("Zona Rural")
                    .latitude(-22.905600)
                    .longitude(-47.060800)
                    .argila(80)
                    .areia(10)
                    .silto(10)
                    .raioSoloKm(14)
                    .tipoSolo(tp11)
                    .usuario(user6)
                    .build());

            EnderecoPlantio end12 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("ba980993-5666-4b90-b1d6-f0a0965130f3"))
                    .nome("Fazenda Pantanal")
                    .cep("69000000")
                    .logradouro("Fazenda Pantanal")
                    .cidade("Cuiaba")
                    .estado("Mato Grosso")
                    .bairro("Area Alagavel")
                    .latitude(-15.601400)
                    .longitude(-56.097900)
                    .argila(60)
                    .areia(15)
                    .silto(25)
                    .raioSoloKm(12)
                    .tipoSolo(tp12)
                    .usuario(user6)
                    .build());

            EnderecoPlantio end13 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("e48f23a5-d162-4595-9037-59dbd9ff4960"))
                    .nome("Fazenda Terra Roxa")
                    .cep("79000000")
                    .logradouro("Fazenda Terra Roxa")
                    .cidade("Dourados")
                    .estado("Mato Grosso do Sul")
                    .bairro("Interior")
                    .latitude(-22.223100)
                    .longitude(-54.812000)
                    .argila(90)
                    .areia(5)
                    .silto(5)
                    .raioSoloKm(16)
                    .tipoSolo(tp13)
                    .usuario(user7)
                    .build());

            EnderecoPlantio end14 = enderecoPlantioRep.save(EnderecoPlantio.builder()
                    .id(UUID.fromString("4291fe5e-b937-4854-9ead-7582a54d84e9"))
                    .nome("Area Experimental")
                    .cep("01000000")
                    .logradouro("Area Experimental")
                    .cidade("Sao Paulo")
                    .estado("Sao Paulo")
                    .bairro("Centro")
                    .latitude(-23.550520)
                    .longitude(-46.633308)
                    .argila(33)
                    .areia(33)
                    .silto(34)
                    .raioSoloKm(10)
                    .tipoSolo(tp14)
                    .usuario(user7)
                    .build());

            Defensivo glifosato = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("f3d9d6d6-49f2-4e8f-b7d6-3b94a6b7f001"))
                    .nome("Glifosato")
                    .tipo("HERBICIDA")
                    .build());

            Defensivo atrazina = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("c0e4e6b1-6d27-49f8-97f0-8d2d2d3f0002"))
                    .nome("Atrazina")
                    .tipo("HERBICIDA")
                    .build());

            Defensivo mancozebe = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("d9b84f71-f52f-43df-a5e7-1c63e0cf0003"))
                    .nome("Mancozebe")
                    .tipo("FUNGICIDA")
                    .build());

            Defensivo clorpirifos = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("7f0f35a4-4d1c-45f5-a2f8-3e9f1ab00004"))
                    .nome("Clorpirifós")
                    .tipo("INSETICIDA")
                    .build());

            Defensivo paraquate = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("aa17eb96-8b6d-45c8-8fd2-54bfe7d00005"))
                    .nome("Paraquate")
                    .tipo("HERBICIDA")
                    .build());

            Defensivo imidacloprido = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("eb69a71c-c2b4-4d92-aac5-58db4e800006"))
                    .nome("Imidacloprido")
                    .tipo("INSETICIDA")
                    .build());

            Defensivo tebuconazol = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("f84656e1-1d42-46e8-a1f6-7bc5ec500007"))
                    .nome("Tebuconazol")
                    .tipo("FUNGICIDA")
                    .build());

            Defensivo fipronil = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("2c57a08a-0a0f-4a24-9d65-89b6cf900008"))
                    .nome("Fipronil")
                    .tipo("INSETICIDA")
                    .build());

            Defensivo doisQuatroD = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("9f4f49df-c39d-4d8d-9237-7d7e9b300009"))
                    .nome("2,4-D")
                    .tipo("HERBICIDA")
                    .build());

            Defensivo azoxistrobina = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("3b4f6aaf-f0f5-4f3c-bf3e-b6fca8100010"))
                    .nome("Azoxistrobina")
                    .tipo("FUNGICIDA")
                    .build());



            Plantio mandioca = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("4d9f5671-8371-4a29-8342-b292ffe2b939"))
                    .nome("Mandioca")
                    .tempMin(22)
                    .tempMax(34)
                    .aguaMM(40)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER))
                    .urlImg("https://exemplo.com/mandioca.png")
                    .tiposSolo(Set.of(tp1))
                    .defensivos(Set.of(glifosato, mancozebe))
                    .build());

            Plantio amendoim = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("b03a3726-0b59-46dd-903c-05a0c1e713f7"))
                    .nome("Amendoim")
                    .tempMin(20)
                    .tempMax(32)
                    .aguaMM(35)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/amendoim.png")
                    .tiposSolo(Set.of(tp1))
                    .defensivos(Set.of(atrazina, tebuconazol))
                    .build());

            Plantio melancia = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("cc6d8c2c-4560-4b71-a75f-9ce35a959dd6"))
                    .nome("Melancia")
                    .tempMin(20)
                    .tempMax(35)
                    .aguaMM(50)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER))
                    .urlImg("https://exemplo.com/melancia.png")
                    .tiposSolo(Set.of(tp2))
                    .defensivos(Set.of(mancozebe, azoxistrobina))
                    .build());

            Plantio milho_verde = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("7831f3c7-51dc-4d7f-b459-1e3ed87814a9"))
                    .nome("Milho Verde")
                    .tempMin(18)
                    .tempMax(33)
                    .aguaMM(45)
                    .mesesIdeais(Set.of(Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/milho_verde.png")
                    .tiposSolo(Set.of(tp2))
                    .defensivos(Set.of(atrazina, clorpirifos))
                    .build());

            Plantio feijao = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("9c16ce2a-7316-41c1-9ca4-905d289b5372"))
                    .nome("Feijão")
                    .tempMin(18)
                    .tempMax(30)
                    .aguaMM(55)
                    .mesesIdeais(Set.of(Month.OCTOBER, Month.NOVEMBER))
                    .urlImg("https://exemplo.com/feijao.png")
                    .tiposSolo(Set.of(tp3))
                    .defensivos(Set.of(mancozebe, imidacloprido))
                    .build());

            Plantio sorgo = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("b3129aa5-028f-416c-8761-4d87143c846b"))
                    .nome("Sorgo")
                    .tempMin(20)
                    .tempMax(36)
                    .aguaMM(40)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/sorgo.png")
                    .tiposSolo(Set.of(tp3))
                    .defensivos(Set.of(glifosato, atrazina))
                    .build());

            Plantio cafe = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("97abc9f5-ec88-44c3-85a8-a678c9d23973"))
                    .nome("Café")
                    .tempMin(18)
                    .tempMax(26)
                    .aguaMM(60)
                    .mesesIdeais(Set.of(Month.MARCH, Month.APRIL, Month.MAY))
                    .urlImg("https://exemplo.com/cafe.png")
                    .tiposSolo(Set.of(tp4))
                    .defensivos(Set.of(tebuconazol, azoxistrobina))
                    .build());

            Plantio cana_acucar = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("84485c05-3790-4c10-a47e-7032fc4a88ad"))
                    .nome("Cana-de-açúcar")
                    .tempMin(20)
                    .tempMax(35)
                    .aguaMM(70)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/cana.png")
                    .tiposSolo(Set.of(tp4))
                    .defensivos(Set.of(glifosato, paraquate))
                    .build());

            Plantio alface = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("1ae97b15-6a2e-4675-b199-43759e8f4551"))
                    .nome("Alface")
                    .tempMin(15)
                    .tempMax(25)
                    .aguaMM(50)
                    .mesesIdeais(Set.of(Month.MARCH, Month.APRIL, Month.MAY))
                    .urlImg("https://exemplo.com/alface.png")
                    .tiposSolo(Set.of(tp5))
                    .defensivos(Set.of(imidacloprido, fipronil))
                    .build());

            Plantio tomate = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("30833675-f4a3-41bf-89ed-bc2d673ccb9a"))
                    .nome("Tomate")
                    .tempMin(18)
                    .tempMax(28)
                    .aguaMM(55)
                    .mesesIdeais(Set.of(Month.AUGUST, Month.SEPTEMBER))
                    .urlImg("https://exemplo.com/tomate.png")
                    .tiposSolo(Set.of(tp5))
                    .defensivos(Set.of(mancozebe, azoxistrobina))
                    .build());

            Plantio trigo = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("0d2e8c47-af6b-43fa-9358-17df42793b36"))
                    .nome("Trigo")
                    .tempMin(12)
                    .tempMax(24)
                    .aguaMM(45)
                    .mesesIdeais(Set.of(Month.MAY, Month.JUNE))
                    .urlImg("https://exemplo.com/trigo.png")
                    .tiposSolo(Set.of(tp6))
                    .defensivos(Set.of(tebuconazol, atrazina))
                    .build());

            Plantio cevada = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("06b2afcd-3fd0-42f7-9276-d77a46c87938"))
                    .nome("Cevada")
                    .tempMin(10)
                    .tempMax(22)
                    .aguaMM(40)
                    .mesesIdeais(Set.of(Month.MAY, Month.JUNE))
                    .urlImg("https://exemplo.com/cevada.png")
                    .tiposSolo(Set.of(tp6))
                    .defensivos(Set.of(tebuconazol, azoxistrobina))
                    .build());

            Plantio soja = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("06905ab5-fe14-4c50-883a-25e5a534bfe2"))
                    .nome("Soja")
                    .tempMin(18)
                    .tempMax(30)
                    .aguaMM(50)
                    .mesesIdeais(Set.of(Month.OCTOBER, Month.NOVEMBER))
                    .urlImg("https://exemplo.com/soja.png")
                    .tiposSolo(Set.of(tp8))
                    .defensivos(Set.of(glifosato, doisQuatroD))
                    .build());

            Plantio milho = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("e6de3d74-cccd-407f-a44f-5f276df623cd"))
                    .nome("Milho")
                    .tempMin(20)
                    .tempMax(35)
                    .aguaMM(45)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/milho.png")
                    .tiposSolo(Set.of(tp8))
                    .defensivos(Set.of(atrazina, clorpirifos))
                    .build());

            Plantio arroz = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("e3ab1402-049e-4973-978a-bd7e75791759"))
                    .nome("Arroz")
                    .tempMin(20)
                    .tempMax(35)
                    .aguaMM(80)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/arroz.png")
                    .tiposSolo(Set.of(tp11))
                    .defensivos(Set.of(paraquate, fipronil))
                    .build());

            Plantio feijao_preto = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("82ff98a3-82dd-4a74-a858-83cd8e1d9c2f"))
                    .nome("Feijão Preto")
                    .tempMin(18)
                    .tempMax(30)
                    .aguaMM(55)
                    .mesesIdeais(Set.of(Month.OCTOBER, Month.NOVEMBER))
                    .urlImg("https://exemplo.com/feijao_preto.png")
                    .tiposSolo(Set.of(tp11))
                    .defensivos(Set.of(mancozebe, imidacloprido))
                    .build());

            analisePlantioRep.save(AnalisePlantio.builder()
                    .id(UUID.fromString("87b5c5d5-f3b4-4ee5-9f3d-c2996d0d505e"))
                    .data(Date.valueOf("2026-05-10"))
                    .tempMax(30)
                    .tempMin(22)
                    .umidadeMed(85)
                    .adequadoPlantio(95)
                    .nivelRisco("BAIXO RISCO")
                    .recomendacao("Condições ideais para soja")
                    .usuario(user1)
                    .enderecoPlantio(end1)
                    .plantio(soja)
                    .build());

            analisePlantioRep.save(AnalisePlantio.builder()
                    .id(UUID.fromString("73c247dc-3eec-413d-b082-a733b069da56"))
                    .data(Date.valueOf("2026-05-11"))
                    .tempMax(33)
                    .tempMin(20)
                    .umidadeMed(80)
                    .adequadoPlantio(90)
                    .nivelRisco("BAIXO RISCO")
                    .recomendacao("Clima favorável ao milho")
                    .usuario(user1)
                    .enderecoPlantio(end2)
                    .plantio(milho)
                    .build());

            analisePlantioRep.save(AnalisePlantio.builder()
                    .id(UUID.fromString("0b37e060-e6cf-4ec7-b535-2f0aae15e2d6"))
                    .data(Date.valueOf("2026-05-12"))
                    .tempMax(28)
                    .tempMin(18)
                    .umidadeMed(88)
                    .adequadoPlantio(61)
                    .nivelRisco("MODERADO")
                    .recomendacao("Boa compatibilidade com feijão")
                    .usuario(user2)
                    .enderecoPlantio(end3)
                    .plantio(feijao)
                    .build());

            analisePlantioRep.save(AnalisePlantio.builder()
                    .id(UUID.fromString("dfdb9521-7be2-48e8-b699-c82bd4cf0ccb"))
                    .data(Date.valueOf("2026-05-13"))
                    .tempMax(26)
                    .tempMin(18)
                    .umidadeMed(75)
                    .adequadoPlantio(85)
                    .nivelRisco("BAIXO RISCO")
                    .recomendacao("Excelente para café")
                    .usuario(user2)
                    .enderecoPlantio(end4)
                    .plantio(cafe)
                    .build());

            analisePlantioRep.save(AnalisePlantio.builder()
                    .id(UUID.fromString("9b4f3186-f505-4078-9b01-7b14019ed99a"))
                    .data(Date.valueOf("2026-05-14"))
                    .tempMax(35)
                    .tempMin(24)
                    .umidadeMed(90)
                    .adequadoPlantio(97)
                    .nivelRisco("BAIXO RISCO")
                    .recomendacao("Alta produtividade esperada")
                    .usuario(user3)
                    .enderecoPlantio(end5)
                    .plantio(cana_acucar)
                    .build());

            analisePlantioRep.save(AnalisePlantio.builder()
                    .id(UUID.fromString("f55b9b31-901c-4d66-bacb-21106f4130a3"))
                    .data(Date.valueOf("2026-05-15"))
                    .tempMax(24)
                    .tempMin(15)
                    .umidadeMed(92)
                    .adequadoPlantio(68)
                    .nivelRisco("MODERADO")
                    .recomendacao("Requer irrigação constante")
                    .usuario(user3)
                    .enderecoPlantio(end6)
                    .plantio(alface)
                    .build());

            analisePlantioRep.save(AnalisePlantio.builder()
                    .id(UUID.fromString("c335f1a8-7ec8-4590-bb9a-d4cca84324bc"))
                    .data(Date.valueOf("2026-05-16"))
                    .tempMax(28)
                    .tempMin(18)
                    .umidadeMed(86)
                    .adequadoPlantio(90)
                    .nivelRisco("BAIXO RISCO")
                    .recomendacao("Boa produção de tomate")
                    .usuario(user4)
                    .enderecoPlantio(end7)
                    .plantio(tomate)
                    .build());

            analisePlantioRep.save(AnalisePlantio.builder()
                    .id(UUID.fromString("2ab2a356-fdf1-4cf9-83f1-b6e6a20dfbed"))
                    .data(Date.valueOf("2026-05-17"))
                    .tempMax(22)
                    .tempMin(12)
                    .umidadeMed(70)
                    .adequadoPlantio(24)
                    .nivelRisco("ALTO RISCO")
                    .recomendacao("Clima frio necessário")
                    .usuario(user4)
                    .enderecoPlantio(end8)
                    .plantio(trigo)
                    .build());

            analisePlantioRep.save(AnalisePlantio.builder()
                    .id(UUID.fromString("9cdb1dbe-5662-4ea8-a0c3-cdcebac3da3b"))
                    .data(Date.valueOf("2026-05-18"))
                    .tempMax(35)
                    .tempMin(20)
                    .umidadeMed(95)
                    .adequadoPlantio(98)
                    .nivelRisco("BAIXO RISCO")
                    .recomendacao("Excelente para arroz irrigado")
                    .usuario(user5)
                    .enderecoPlantio(end9)
                    .plantio(arroz)
                    .build());

            analisePlantioRep.save(AnalisePlantio.builder()
                    .id(UUID.fromString("91af5813-457a-4ac0-9989-1e72f41f4ab4"))
                    .data(Date.valueOf("2026-05-19"))
                    .tempMax(30)
                    .tempMin(18)
                    .umidadeMed(85)
                    .adequadoPlantio(59)
                    .nivelRisco("MODERADO")
                    .recomendacao("Boa adaptação ao solo")
                    .usuario(user5)
                    .enderecoPlantio(end10)
                    .plantio(feijao_preto)
                    .build());
        };

    }
}