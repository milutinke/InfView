/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     5/31/2019 5:02:22 PM                         */
/*==============================================================*/


if exists (select 1
            from  sysindexes
           where  id    = object_id('CENOVNIK')
            and   name  = 'KLASIFIKACIJA_CENOVNIKA_FK'
            and   indid > 0
            and   indid < 255)
   drop index CENOVNIK.KLASIFIKACIJA_CENOVNIKA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('CENOVNIK')
            and   name  = 'IMA_CENOVNIK_FK'
            and   indid > 0
            and   indid < 255)
   drop index CENOVNIK.IMA_CENOVNIK_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('CENOVNIK')
            and   type = 'U')
   drop table CENOVNIK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('DRZAVA')
            and   name  = 'PRAVNI_NASLEDNIK_FK'
            and   indid > 0
            and   indid < 255)
   drop index DRZAVA.PRAVNI_NASLEDNIK_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('DRZAVA')
            and   name  = 'TEKUCE_URE_ENJE_FK'
            and   indid > 0
            and   indid < 255)
   drop index DRZAVA.TEKUCE_URE_ENJE_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('DRZAVA')
            and   name  = 'GLAVNI_GRAD_FK'
            and   indid > 0
            and   indid < 255)
   drop index DRZAVA.GLAVNI_GRAD_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('DRZAVA')
            and   type = 'U')
   drop table DRZAVA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('DRZAVNO_URE_ENJE')
            and   type = 'U')
   drop table DRZAVNO_URE_ENJE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ISTORIJA_DRZAVNOG_URE_ENJA')
            and   name  = 'URE_ENJE_DRZAVE_FK'
            and   indid > 0
            and   indid < 255)
   drop index ISTORIJA_DRZAVNOG_URE_ENJA.URE_ENJE_DRZAVE_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ISTORIJA_DRZAVNOG_URE_ENJA')
            and   name  = 'PROMENA_URE_ENJA_FK'
            and   indid > 0
            and   indid < 255)
   drop index ISTORIJA_DRZAVNOG_URE_ENJA.PROMENA_URE_ENJA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ISTORIJA_DRZAVNOG_URE_ENJA')
            and   type = 'U')
   drop table ISTORIJA_DRZAVNOG_URE_ENJA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('IZVRSILAC_USLUGE')
            and   name  = 'IZVRSILAC_USLUGE2_FK'
            and   indid > 0
            and   indid < 255)
   drop index IZVRSILAC_USLUGE.IZVRSILAC_USLUGE2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('IZVRSILAC_USLUGE')
            and   name  = 'IZVRSILAC_USLUGE_FK'
            and   indid > 0
            and   indid < 255)
   drop index IZVRSILAC_USLUGE.IZVRSILAC_USLUGE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('IZVRSILAC_USLUGE')
            and   type = 'U')
   drop table IZVRSILAC_USLUGE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('KLIJENT')
            and   name  = 'KLASIFIKACIJA_KLIJENTA_FK'
            and   indid > 0
            and   indid < 255)
   drop index KLIJENT.KLASIFIKACIJA_KLIJENTA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('KLIJENT')
            and   name  = 'REGISTROVANI_FK'
            and   indid > 0
            and   indid < 255)
   drop index KLIJENT.REGISTROVANI_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('KLIJENT')
            and   type = 'U')
   drop table KLIJENT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('MESNA_ZAJEDNICA')
            and   name  = 'MESNA_KANCELARIJA_FK'
            and   indid > 0
            and   indid < 255)
   drop index MESNA_ZAJEDNICA.MESNA_KANCELARIJA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('MESNA_ZAJEDNICA')
            and   name  = 'JEDINICE_LOKALNE_SAMOUPRAVE_FK'
            and   indid > 0
            and   indid < 255)
   drop index MESNA_ZAJEDNICA.JEDINICE_LOKALNE_SAMOUPRAVE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('MESNA_ZAJEDNICA')
            and   type = 'U')
   drop table MESNA_ZAJEDNICA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('NASELJENO_MESTO')
            and   name  = 'ADMINSTRATIVNO_SEDISTE_FK'
            and   indid > 0
            and   indid < 255)
   drop index NASELJENO_MESTO.ADMINSTRATIVNO_SEDISTE_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('NASELJENO_MESTO')
            and   name  = 'POSEDUJE_MESTA_FK'
            and   indid > 0
            and   indid < 255)
   drop index NASELJENO_MESTO.POSEDUJE_MESTA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('NASELJENO_MESTO')
            and   type = 'U')
   drop table NASELJENO_MESTO
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('OBAVLJENE_USLUGE')
            and   name  = 'OBAVLJENE_USLUGE2_FK'
            and   indid > 0
            and   indid < 255)
   drop index OBAVLJENE_USLUGE.OBAVLJENE_USLUGE2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('OBAVLJENE_USLUGE')
            and   name  = 'OBAVLJENE_USLUGE_FK'
            and   indid > 0
            and   indid < 255)
   drop index OBAVLJENE_USLUGE.OBAVLJENE_USLUGE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('OBAVLJENE_USLUGE')
            and   type = 'U')
   drop table OBAVLJENE_USLUGE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('OPSTINA')
            and   name  = 'ADMINSTRATIVNO_SEDISTE2_FK'
            and   indid > 0
            and   indid < 255)
   drop index OPSTINA.ADMINSTRATIVNO_SEDISTE2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('OPSTINA')
            and   name  = 'LOKALNA_SAMOUPRAVA_FK'
            and   indid > 0
            and   indid < 255)
   drop index OPSTINA.LOKALNA_SAMOUPRAVA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('OPSTINA')
            and   type = 'U')
   drop table OPSTINA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('OPSTINA_NASELJE')
            and   name  = 'OPSTINA_NASELJE2_FK'
            and   indid > 0
            and   indid < 255)
   drop index OPSTINA_NASELJE.OPSTINA_NASELJE2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('OPSTINA_NASELJE')
            and   name  = 'OPSTINA_NASELJE_FK'
            and   indid > 0
            and   indid < 255)
   drop index OPSTINA_NASELJE.OPSTINA_NASELJE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('OPSTINA_NASELJE')
            and   type = 'U')
   drop table OPSTINA_NASELJE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ORGANIZACIONE_JEDINICE')
            and   name  = 'SEDISRE_JEDINICE_FK'
            and   indid > 0
            and   indid < 255)
   drop index ORGANIZACIONE_JEDINICE.SEDISRE_JEDINICE_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ORGANIZACIONE_JEDINICE')
            and   name  = 'KLASIFIKACIJA_ORGJED_FK'
            and   indid > 0
            and   indid < 255)
   drop index ORGANIZACIONE_JEDINICE.KLASIFIKACIJA_ORGJED_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ORGANIZACIONE_JEDINICE')
            and   name  = 'UNUTRASNJA_ORGANIZACIJA_FK'
            and   indid > 0
            and   indid < 255)
   drop index ORGANIZACIONE_JEDINICE.UNUTRASNJA_ORGANIZACIJA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ORGANIZACIONE_JEDINICE')
            and   type = 'U')
   drop table ORGANIZACIONE_JEDINICE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_PARTNERI')
            and   name  = 'U_KOM_ODNOSU_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_PARTNERI.U_KOM_ODNOSU_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_PARTNERI')
            and   name  = 'PARNTER_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_PARTNERI.PARNTER_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_PARTNERI')
            and   name  = 'POSLOVNI_SISTEM_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_PARTNERI.POSLOVNI_SISTEM_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POSLOVNI_PARTNERI')
            and   type = 'U')
   drop table POSLOVNI_PARTNERI
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_SISTEM')
            and   name  = 'KLASIFIKACIJA_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_SISTEM.KLASIFIKACIJA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_SISTEM')
            and   name  = 'OSNOVNA_DELATNOST_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_SISTEM.OSNOVNA_DELATNOST_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_SISTEM')
            and   name  = 'SEDISTE_KOMPANIJE_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_SISTEM.SEDISTE_KOMPANIJE_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_SISTEM')
            and   name  = 'REGISTROVANI_POSLOVNI_SISTEMI_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_SISTEM.REGISTROVANI_POSLOVNI_SISTEMI_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POSLOVNI_SISTEM')
            and   type = 'U')
   drop table POSLOVNI_SISTEM
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE')
            and   name  = 'POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE2_FK'
            and   indid > 0
            and   indid < 255)
   drop index POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE.POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE')
            and   name  = 'POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE_FK'
            and   indid > 0
            and   indid < 255)
   drop index POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE.POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE')
            and   type = 'U')
   drop table POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROIZVOD_ZA_SERVISIRANJE')
            and   name  = 'EVIDENCIJA_O_PROIZVODU_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROIZVOD_ZA_SERVISIRANJE.EVIDENCIJA_O_PROIZVODU_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROIZVOD_ZA_SERVISIRANJE')
            and   name  = 'KLASIFIKACIJA_PROIZVODA_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROIZVOD_ZA_SERVISIRANJE.KLASIFIKACIJA_PROIZVODA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROIZVOD_ZA_SERVISIRANJE')
            and   name  = 'U_VLASNISTVU_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROIZVOD_ZA_SERVISIRANJE.U_VLASNISTVU_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PROIZVOD_ZA_SERVISIRANJE')
            and   type = 'U')
   drop table PROIZVOD_ZA_SERVISIRANJE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('REGION')
            and   name  = 'ADMINISTRATIVNI_CENTAR_REGIONA_FK'
            and   indid > 0
            and   indid < 255)
   drop index REGION.ADMINISTRATIVNI_CENTAR_REGIONA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('REGION')
            and   name  = 'MATICNA_DRZAVA_FK'
            and   indid > 0
            and   indid < 255)
   drop index REGION.MATICNA_DRZAVA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('REGION')
            and   name  = 'TIPIZACIJA_REGIONA_FK'
            and   indid > 0
            and   indid < 255)
   drop index REGION.TIPIZACIJA_REGIONA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REGION')
            and   type = 'U')
   drop table REGION
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('REGISTAR_DELATNOSTI')
            and   name  = 'REGISTROVANE_DELATNOSTI_FK'
            and   indid > 0
            and   indid < 255)
   drop index REGISTAR_DELATNOSTI.REGISTROVANE_DELATNOSTI_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REGISTAR_DELATNOSTI')
            and   type = 'U')
   drop table REGISTAR_DELATNOSTI
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('REGISTAR_KLIJENATA')
            and   name  = 'SADRZI_REGISTRE_FK'
            and   indid > 0
            and   indid < 255)
   drop index REGISTAR_KLIJENATA.SADRZI_REGISTRE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REGISTAR_KLIJENATA')
            and   type = 'U')
   drop table REGISTAR_KLIJENATA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('REGISTROVAN_ZA')
            and   name  = 'REGISTROVAN_ZA2_FK'
            and   indid > 0
            and   indid < 255)
   drop index REGISTROVAN_ZA.REGISTROVAN_ZA2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('REGISTROVAN_ZA')
            and   name  = 'REGISTROVAN_ZA_FK'
            and   indid > 0
            and   indid < 255)
   drop index REGISTROVAN_ZA.REGISTROVAN_ZA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REGISTROVAN_ZA')
            and   type = 'U')
   drop table REGISTROVAN_ZA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('RESURSI_SERVISNE_STANICE')
            and   name  = 'KLASIFIKACIJA_RESURSA_FK'
            and   indid > 0
            and   indid < 255)
   drop index RESURSI_SERVISNE_STANICE.KLASIFIKACIJA_RESURSA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('RESURSI_SERVISNE_STANICE')
            and   name  = 'RESURSI_NA_RASPOLAGANJU_FK'
            and   indid > 0
            and   indid < 255)
   drop index RESURSI_SERVISNE_STANICE.RESURSI_NA_RASPOLAGANJU_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('RESURSI_SERVISNE_STANICE')
            and   type = 'U')
   drop table RESURSI_SERVISNE_STANICE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('REZERVACIJA')
            and   name  = 'ZA_KOG_KLIJENTA_FK'
            and   indid > 0
            and   indid < 255)
   drop index REZERVACIJA.ZA_KOG_KLIJENTA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('REZERVACIJA')
            and   name  = 'REZERVACIJA_TERMINA_ZA_OBAVLJANJE_SERVISIRA_FK'
            and   indid > 0
            and   indid < 255)
   drop index REZERVACIJA.REZERVACIJA_TERMINA_ZA_OBAVLJANJE_SERVISIRA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REZERVACIJA')
            and   type = 'U')
   drop table REZERVACIJA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SASTAV_DRZAVE')
            and   name  = 'SASTAV_DRZAVE2_FK'
            and   indid > 0
            and   indid < 255)
   drop index SASTAV_DRZAVE.SASTAV_DRZAVE2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SASTAV_DRZAVE')
            and   name  = 'SASTAV_DRZAVE_FK'
            and   indid > 0
            and   indid < 255)
   drop index SASTAV_DRZAVE.SASTAV_DRZAVE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SASTAV_DRZAVE')
            and   type = 'U')
   drop table SASTAV_DRZAVE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SASTAV_NASELJA')
            and   name  = 'SASTAV_NASELJA2_FK'
            and   indid > 0
            and   indid < 255)
   drop index SASTAV_NASELJA.SASTAV_NASELJA2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SASTAV_NASELJA')
            and   name  = 'SASTAV_NASELJA_FK'
            and   indid > 0
            and   indid < 255)
   drop index SASTAV_NASELJA.SASTAV_NASELJA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SASTAV_NASELJA')
            and   type = 'U')
   drop table SASTAV_NASELJA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SASTAV_ORGANIZACIONE_JEDINICE')
            and   name  = 'SASTAV_ORGANIZACIONE_JEDINICE2_FK'
            and   indid > 0
            and   indid < 255)
   drop index SASTAV_ORGANIZACIONE_JEDINICE.SASTAV_ORGANIZACIONE_JEDINICE2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SASTAV_ORGANIZACIONE_JEDINICE')
            and   name  = 'SASTAV_ORGANIZACIONE_JEDINICE_FK'
            and   indid > 0
            and   indid < 255)
   drop index SASTAV_ORGANIZACIONE_JEDINICE.SASTAV_ORGANIZACIONE_JEDINICE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SASTAV_ORGANIZACIONE_JEDINICE')
            and   type = 'U')
   drop table SASTAV_ORGANIZACIONE_JEDINICE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SASTAV_POSLOVNOG_SISTEMA')
            and   name  = 'SASTAV_POSLOVNOG_SISTEMA2_FK'
            and   indid > 0
            and   indid < 255)
   drop index SASTAV_POSLOVNOG_SISTEMA.SASTAV_POSLOVNOG_SISTEMA2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SASTAV_POSLOVNOG_SISTEMA')
            and   name  = 'SASTAV_POSLOVNOG_SISTEMA_FK'
            and   indid > 0
            and   indid < 255)
   drop index SASTAV_POSLOVNOG_SISTEMA.SASTAV_POSLOVNOG_SISTEMA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SASTAV_POSLOVNOG_SISTEMA')
            and   type = 'U')
   drop table SASTAV_POSLOVNOG_SISTEMA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SASTAV_REGIONA')
            and   name  = 'SASTAV_REGIONA2_FK'
            and   indid > 0
            and   indid < 255)
   drop index SASTAV_REGIONA.SASTAV_REGIONA2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SASTAV_REGIONA')
            and   name  = 'SASTAV_REGIONA_FK'
            and   indid > 0
            and   indid < 255)
   drop index SASTAV_REGIONA.SASTAV_REGIONA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SASTAV_REGIONA')
            and   type = 'U')
   drop table SASTAV_REGIONA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SERVISNA_ISTORIJA')
            and   name  = 'EVIDENCIJA_O_PROIZVODU2_FK'
            and   indid > 0
            and   indid < 255)
   drop index SERVISNA_ISTORIJA.EVIDENCIJA_O_PROIZVODU2_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SERVISNA_ISTORIJA')
            and   type = 'U')
   drop table SERVISNA_ISTORIJA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SERVISNA_STANICA')
            and   type = 'U')
   drop table SERVISNA_STANICA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TIP_CENOVNIKA')
            and   type = 'U')
   drop table TIP_CENOVNIKA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TIP_KLIJENTA')
            and   type = 'U')
   drop table TIP_KLIJENTA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TIP_PROIZVODA')
            and   type = 'U')
   drop table TIP_PROIZVODA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TIP_RADNOG_MESTA')
            and   type = 'U')
   drop table TIP_RADNOG_MESTA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TIP_REGIONA')
            and   type = 'U')
   drop table TIP_REGIONA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TIP_RESURSA')
            and   type = 'U')
   drop table TIP_RESURSA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TIP_USLUGE')
            and   type = 'U')
   drop table TIP_USLUGE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('UKLJUCUJE_MESTA')
            and   name  = 'UKLJUCUJE_MESTA2_FK'
            and   indid > 0
            and   indid < 255)
   drop index UKLJUCUJE_MESTA.UKLJUCUJE_MESTA2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('UKLJUCUJE_MESTA')
            and   name  = 'UKLJUCUJE_MESTA_FK'
            and   indid > 0
            and   indid < 255)
   drop index UKLJUCUJE_MESTA.UKLJUCUJE_MESTA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('UKLJUCUJE_MESTA')
            and   type = 'U')
   drop table UKLJUCUJE_MESTA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('UKLJUCUJE_OPSTINE')
            and   name  = 'UKLJUCUJE_OPSTINE2_FK'
            and   indid > 0
            and   indid < 255)
   drop index UKLJUCUJE_OPSTINE.UKLJUCUJE_OPSTINE2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('UKLJUCUJE_OPSTINE')
            and   name  = 'UKLJUCUJE_OPSTINE_FK'
            and   indid > 0
            and   indid < 255)
   drop index UKLJUCUJE_OPSTINE.UKLJUCUJE_OPSTINE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('UKLJUCUJE_OPSTINE')
            and   type = 'U')
   drop table UKLJUCUJE_OPSTINE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('USLUGE')
            and   name  = 'PRUZA_FK'
            and   indid > 0
            and   indid < 255)
   drop index USLUGE.PRUZA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('USLUGE')
            and   name  = 'KLASIFIKACIJA_USLUGE_FK'
            and   indid > 0
            and   indid < 255)
   drop index USLUGE.KLASIFIKACIJA_USLUGE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('USLUGE')
            and   type = 'U')
   drop table USLUGE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('USLUGE_UNUTAR_CENOVNIKA')
            and   name  = 'USLUGE_UNUTAR_CENOVNIKA2_FK'
            and   indid > 0
            and   indid < 255)
   drop index USLUGE_UNUTAR_CENOVNIKA.USLUGE_UNUTAR_CENOVNIKA2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('USLUGE_UNUTAR_CENOVNIKA')
            and   name  = 'USLUGE_UNUTAR_CENOVNIKA_FK'
            and   indid > 0
            and   indid < 255)
   drop index USLUGE_UNUTAR_CENOVNIKA.USLUGE_UNUTAR_CENOVNIKA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('USLUGE_UNUTAR_CENOVNIKA')
            and   type = 'U')
   drop table USLUGE_UNUTAR_CENOVNIKA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('VRSTA_ORGANIZACIONE_JEDINICE')
            and   type = 'U')
   drop table VRSTA_ORGANIZACIONE_JEDINICE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('VRSTA_PARTNERSTVA')
            and   type = 'U')
   drop table VRSTA_PARTNERSTVA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('VRSTA_POSLOVNOG_SISTEMA')
            and   type = 'U')
   drop table VRSTA_POSLOVNOG_SISTEMA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ZAPOSLENI')
            and   name  = 'KLASIFIKACIJA_RADNOG_MESTA_FK'
            and   indid > 0
            and   indid < 255)
   drop index ZAPOSLENI.KLASIFIKACIJA_RADNOG_MESTA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ZAPOSLENI')
            and   name  = 'ZAPOSLENI_U_FK'
            and   indid > 0
            and   indid < 255)
   drop index ZAPOSLENI.ZAPOSLENI_U_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ZAPOSLENI')
            and   type = 'U')
   drop table ZAPOSLENI
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ZA_KOJE_USLUGE')
            and   name  = 'ZA_KOJE_USLUGE2_FK'
            and   indid > 0
            and   indid < 255)
   drop index ZA_KOJE_USLUGE.ZA_KOJE_USLUGE2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ZA_KOJE_USLUGE')
            and   name  = 'ZA_KOJE_USLUGE_FK'
            and   indid > 0
            and   indid < 255)
   drop index ZA_KOJE_USLUGE.ZA_KOJE_USLUGE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ZA_KOJE_USLUGE')
            and   type = 'U')
   drop table ZA_KOJE_USLUGE
go

/*==============================================================*/
/* Table: CENOVNIK                                              */
/*==============================================================*/
create table CENOVNIK (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   TIP_CENOVNIKA        int                  not null,
   CENOVNIK             int                  not null,
   NAZIV                varchar(50)          not null,
   CENA_USLUGE          double precision     not null,
   constraint PK_CENOVNIK primary key nonclustered (DRZAVA, VRSTA_SISTEMA, POSLOVNI_SISTEM, TIP_CENOVNIKA, CENOVNIK)
)
go

/*==============================================================*/
/* Index: IMA_CENOVNIK_FK                                       */
/*==============================================================*/
create index IMA_CENOVNIK_FK on CENOVNIK (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC
)
go

/*==============================================================*/
/* Index: KLASIFIKACIJA_CENOVNIKA_FK                            */
/*==============================================================*/
create index KLASIFIKACIJA_CENOVNIKA_FK on CENOVNIK (
TIP_CENOVNIKA ASC
)
go

/*==============================================================*/
/* Table: DRZAVA                                                */
/*==============================================================*/
create table DRZAVA (
   DR_IDENTIFIKATOR     char(3)              not null,
   PRAVNI_NASLEDNIK     char(3)              null,
   TEKUCE_DRZAVNO_UREDJENJE char(1)              not null,
   GLAVNI_GRAD          int                  null,
   DR_NAZIV             varchar(80)          not null,
   DR_GRB               image                null,
   DR_ZASTAVA           image                null,
   DR_HIMNA             image                null,
   constraint PK_DRZAVA primary key nonclustered (DR_IDENTIFIKATOR)
)
go

/*==============================================================*/
/* Index: GLAVNI_GRAD_FK                                        */
/*==============================================================*/
create index GLAVNI_GRAD_FK on DRZAVA (
DR_IDENTIFIKATOR ASC,
GLAVNI_GRAD ASC
)
go

/*==============================================================*/
/* Index: TEKUCE_URE_ENJE_FK                                    */
/*==============================================================*/
create index TEKUCE_URE_ENJE_FK on DRZAVA (
TEKUCE_DRZAVNO_UREDJENJE ASC
)
go

/*==============================================================*/
/* Index: PRAVNI_NASLEDNIK_FK                                   */
/*==============================================================*/
create index PRAVNI_NASLEDNIK_FK on DRZAVA (
PRAVNI_NASLEDNIK ASC
)
go

/*==============================================================*/
/* Table: DRZAVNO_URE_ENJE                                      */
/*==============================================================*/
create table DRZAVNO_URE_ENJE (
   DU_OZNAKA            char(1)              not null,
   DU_NAZIV             varchar(40)          not null,
   constraint PK_DRZAVNO_URE_ENJE primary key nonclustered (DU_OZNAKA)
)
go

/*==============================================================*/
/* Table: ISTORIJA_DRZAVNOG_URE_ENJA                            */
/*==============================================================*/
create table ISTORIJA_DRZAVNOG_URE_ENJA (
   DRZAVA               char(3)              not null,
   DRZAVNO_UREDJENJE    char(1)              not null,
   IST_REDNI_BROJ       numeric(1,0)         not null,
   IST_OD               datetime             not null,
   IST_DO               datetime             null,
   constraint PK_ISTORIJA_DRZAVNOG_URE_ENJA primary key nonclustered (DRZAVA, DRZAVNO_UREDJENJE, IST_REDNI_BROJ)
)
go

/*==============================================================*/
/* Index: PROMENA_URE_ENJA_FK                                   */
/*==============================================================*/
create index PROMENA_URE_ENJA_FK on ISTORIJA_DRZAVNOG_URE_ENJA (
DRZAVA ASC
)
go

/*==============================================================*/
/* Index: URE_ENJE_DRZAVE_FK                                    */
/*==============================================================*/
create index URE_ENJE_DRZAVE_FK on ISTORIJA_DRZAVNOG_URE_ENJA (
DRZAVNO_UREDJENJE ASC
)
go

/*==============================================================*/
/* Table: IZVRSILAC_USLUGE                                      */
/*==============================================================*/
create table IZVRSILAC_USLUGE (
   DR_IDENTIFIKATOR     char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   ZAPOSLEN             int                  not null,
   RADNO_MESTO          int                  not null,
   TIP_USLUGE           int                  not null,
   POSLOVNI_SISTEM      int                  not null,
   USLUGA               int                  not null,
   constraint PK_IZVRSILAC_USLUGE primary key (DR_IDENTIFIKATOR, VRSTA_SISTEMA, ZAPOSLEN, RADNO_MESTO, TIP_USLUGE, POSLOVNI_SISTEM, USLUGA)
)
go

/*==============================================================*/
/* Index: IZVRSILAC_USLUGE_FK                                   */
/*==============================================================*/
create index IZVRSILAC_USLUGE_FK on IZVRSILAC_USLUGE (
DR_IDENTIFIKATOR ASC,
VRSTA_SISTEMA ASC,
RADNO_MESTO ASC,
ZAPOSLEN ASC,
ZAPOSLEN ASC
)
go

/*==============================================================*/
/* Index: IZVRSILAC_USLUGE2_FK                                  */
/*==============================================================*/
create index IZVRSILAC_USLUGE2_FK on IZVRSILAC_USLUGE (
DR_IDENTIFIKATOR ASC,
VRSTA_SISTEMA ASC,
TIP_USLUGE ASC,
POSLOVNI_SISTEM ASC,
USLUGA ASC
)
go

/*==============================================================*/
/* Table: KLIJENT                                               */
/*==============================================================*/
create table KLIJENT (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   REGISTAR_KLIJENATA   int                  not null,
   TIP_KLIJENTA         int                  not null,
   KLIJENT              int                  not null,
   IME                  varchar(50)          not null,
   PREZIME              varchar(50)          not null,
   OD                   datetime             null,
   DO                   datetime             null,
   constraint PK_KLIJENT primary key nonclustered (DRZAVA, VRSTA_SISTEMA, POSLOVNI_SISTEM, REGISTAR_KLIJENATA, TIP_KLIJENTA, KLIJENT)
)
go

/*==============================================================*/
/* Index: REGISTROVANI_FK                                       */
/*==============================================================*/
create index REGISTROVANI_FK on KLIJENT (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC,
REGISTAR_KLIJENATA ASC
)
go

/*==============================================================*/
/* Index: KLASIFIKACIJA_KLIJENTA_FK                             */
/*==============================================================*/
create index KLASIFIKACIJA_KLIJENTA_FK on KLIJENT (
TIP_KLIJENTA ASC
)
go

/*==============================================================*/
/* Table: MESNA_ZAJEDNICA                                       */
/*==============================================================*/
create table MESNA_ZAJEDNICA (
   DRZAVA_OPSTINE       char(3)              not null,
   OPSTINA              numeric(5,0)         not null,
   MESNA_ZAJEDNICA      numeric(2,0)         not null,
   MESTO_SEDISTA        int                  null,
   MZ_NAZIV             varchar(80)          not null,
   constraint PK_MESNA_ZAJEDNICA primary key nonclustered (DRZAVA_OPSTINE, OPSTINA, MESNA_ZAJEDNICA)
)
go

/*==============================================================*/
/* Index: JEDINICE_LOKALNE_SAMOUPRAVE_FK                        */
/*==============================================================*/
create index JEDINICE_LOKALNE_SAMOUPRAVE_FK on MESNA_ZAJEDNICA (
DRZAVA_OPSTINE ASC,
OPSTINA ASC
)
go

/*==============================================================*/
/* Index: MESNA_KANCELARIJA_FK                                  */
/*==============================================================*/
create index MESNA_KANCELARIJA_FK on MESNA_ZAJEDNICA (
DRZAVA_OPSTINE ASC,
MESTO_SEDISTA ASC
)
go

/*==============================================================*/
/* Table: NASELJENO_MESTO                                       */
/*==============================================================*/
create table NASELJENO_MESTO (
   DRZAVA               char(3)              not null,
   NM_DENTIFIKATOR      int                  not null,
   OPSTINA_CIJE_JE_ADMINISTRATIVNO_SEDISTE_OVO_MESTO numeric(5,0)         null,
   NM_NAZIV             varchar(40)          not null,
   constraint PK_NASELJENO_MESTO primary key nonclustered (DRZAVA, NM_DENTIFIKATOR)
)
go

/*==============================================================*/
/* Index: POSEDUJE_MESTA_FK                                     */
/*==============================================================*/
create index POSEDUJE_MESTA_FK on NASELJENO_MESTO (
DRZAVA ASC
)
go

/*==============================================================*/
/* Index: ADMINSTRATIVNO_SEDISTE_FK                             */
/*==============================================================*/
create index ADMINSTRATIVNO_SEDISTE_FK on NASELJENO_MESTO (
DRZAVA ASC,
OPSTINA_CIJE_JE_ADMINISTRATIVNO_SEDISTE_OVO_MESTO ASC
)
go

/*==============================================================*/
/* Table: OBAVLJENE_USLUGE                                      */
/*==============================================================*/
create table OBAVLJENE_USLUGE (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   REGISTAR_KLIJENATA   int                  not null,
   TIP_KLIJENTA         int                  not null,
   KLIJENT              int                  not null,
   TIP_PROIZVODA        int                  not null,
   PROIZVOD             int                  not null,
   SERVISNA_ISTORIJA    int                  not null,
   TIP_USLUGE           int                  not null,
   POSLOVNI_SISTEM      int                  not null,
   USLUGA               int                  not null,
   constraint PK_OBAVLJENE_USLUGE primary key (DRZAVA, VRSTA_SISTEMA, REGISTAR_KLIJENATA, TIP_KLIJENTA, KLIJENT, TIP_PROIZVODA, PROIZVOD, SERVISNA_ISTORIJA, TIP_USLUGE, POSLOVNI_SISTEM, USLUGA)
)
go

/*==============================================================*/
/* Index: OBAVLJENE_USLUGE_FK                                   */
/*==============================================================*/
create index OBAVLJENE_USLUGE_FK on OBAVLJENE_USLUGE (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC,
REGISTAR_KLIJENATA ASC,
TIP_KLIJENTA ASC,
KLIJENT ASC,
TIP_PROIZVODA ASC,
PROIZVOD ASC,
SERVISNA_ISTORIJA ASC
)
go

/*==============================================================*/
/* Index: OBAVLJENE_USLUGE2_FK                                  */
/*==============================================================*/
create index OBAVLJENE_USLUGE2_FK on OBAVLJENE_USLUGE (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
TIP_USLUGE ASC,
POSLOVNI_SISTEM ASC,
USLUGA ASC
)
go

/*==============================================================*/
/* Table: OPSTINA                                               */
/*==============================================================*/
create table OPSTINA (
   DRZAVA               char(3)              not null,
   OP_OZNAKA            numeric(5,0)         not null,
   ADMINISTRATIVNO_SEDISTE_OPSTINE int                  not null,
   PO_NAZIV             varchar(60)          not null,
   constraint PK_OPSTINA primary key nonclustered (DRZAVA, OP_OZNAKA)
)
go

/*==============================================================*/
/* Index: LOKALNA_SAMOUPRAVA_FK                                 */
/*==============================================================*/
create index LOKALNA_SAMOUPRAVA_FK on OPSTINA (
DRZAVA ASC
)
go

/*==============================================================*/
/* Index: ADMINSTRATIVNO_SEDISTE2_FK                            */
/*==============================================================*/
create index ADMINSTRATIVNO_SEDISTE2_FK on OPSTINA (
DRZAVA ASC,
ADMINISTRATIVNO_SEDISTE_OPSTINE ASC
)
go

/*==============================================================*/
/* Table: OPSTINA_NASELJE                                       */
/*==============================================================*/
create table OPSTINA_NASELJE (
   DRZAVA_OPSTINE       char(3)              not null,
   OPSTINA              numeric(5,0)         not null,
   NASELJENO_MESTO      int                  not null,
   constraint PK_OPSTINA_NASELJE primary key (DRZAVA_OPSTINE, OPSTINA, NASELJENO_MESTO)
)
go

/*==============================================================*/
/* Index: OPSTINA_NASELJE_FK                                    */
/*==============================================================*/
create index OPSTINA_NASELJE_FK on OPSTINA_NASELJE (
DRZAVA_OPSTINE ASC,
OPSTINA ASC
)
go

/*==============================================================*/
/* Index: OPSTINA_NASELJE2_FK                                   */
/*==============================================================*/
create index OPSTINA_NASELJE2_FK on OPSTINA_NASELJE (
DRZAVA_OPSTINE ASC,
NASELJENO_MESTO ASC
)
go

/*==============================================================*/
/* Table: ORGANIZACIONE_JEDINICE                                */
/*==============================================================*/
create table ORGANIZACIONE_JEDINICE (
   DRZAVA_REGISTRACIJE  char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   VRSTA_JEDINICE       char(2)              not null,
   ORGANIZACIONA_JEDINICA int                  not null,
   SEDISTE_JEDINICE     int                  null,
   OJ_NAZIV             varchar(120)         not null,
   constraint PK_ORGANIZACIONE_JEDINICE primary key nonclustered (DRZAVA_REGISTRACIJE, VRSTA_SISTEMA, POSLOVNI_SISTEM, VRSTA_JEDINICE, ORGANIZACIONA_JEDINICA)
)
go

/*==============================================================*/
/* Index: UNUTRASNJA_ORGANIZACIJA_FK                            */
/*==============================================================*/
create index UNUTRASNJA_ORGANIZACIJA_FK on ORGANIZACIONE_JEDINICE (
DRZAVA_REGISTRACIJE ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC
)
go

/*==============================================================*/
/* Index: KLASIFIKACIJA_ORGJED_FK                               */
/*==============================================================*/
create index KLASIFIKACIJA_ORGJED_FK on ORGANIZACIONE_JEDINICE (
VRSTA_JEDINICE ASC
)
go

/*==============================================================*/
/* Index: SEDISRE_JEDINICE_FK                                   */
/*==============================================================*/
create index SEDISRE_JEDINICE_FK on ORGANIZACIONE_JEDINICE (
DRZAVA_REGISTRACIJE ASC,
SEDISTE_JEDINICE ASC
)
go

/*==============================================================*/
/* Table: POSLOVNI_PARTNERI                                     */
/*==============================================================*/
create table POSLOVNI_PARTNERI (
   DRZAVA_REGISTRACIJE  char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   DRZAVA_POSLOVNOG_PARTNERA char(3)              not null,
   VRSTA_POSLOVNOG_PARTNERA char(2)              not null,
   POSLOVNI_PARTNER     int                  not null,
   VRSTA_ODNOSA         char(1)              not null,
   PAR_REDNI_BROJ       numeric(2,0)         not null,
   PAR_OD               datetime             null,
   PAR_DO               datetime             null,
   constraint PK_POSLOVNI_PARTNERI primary key nonclustered (DRZAVA_POSLOVNOG_PARTNERA, DRZAVA_REGISTRACIJE, VRSTA_POSLOVNOG_PARTNERA, VRSTA_SISTEMA, POSLOVNI_SISTEM, POSLOVNI_PARTNER, VRSTA_ODNOSA, PAR_REDNI_BROJ)
)
go

/*==============================================================*/
/* Index: POSLOVNI_SISTEM_FK                                    */
/*==============================================================*/
create index POSLOVNI_SISTEM_FK on POSLOVNI_PARTNERI (
DRZAVA_REGISTRACIJE ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC
)
go

/*==============================================================*/
/* Index: PARNTER_FK                                            */
/*==============================================================*/
create index PARNTER_FK on POSLOVNI_PARTNERI (
DRZAVA_POSLOVNOG_PARTNERA ASC,
VRSTA_POSLOVNOG_PARTNERA ASC,
POSLOVNI_PARTNER ASC
)
go

/*==============================================================*/
/* Index: U_KOM_ODNOSU_FK                                       */
/*==============================================================*/
create index U_KOM_ODNOSU_FK on POSLOVNI_PARTNERI (
VRSTA_ODNOSA ASC
)
go

/*==============================================================*/
/* Table: POSLOVNI_SISTEM                                       */
/*==============================================================*/
create table POSLOVNI_SISTEM (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   PS_ID                int                  not null,
   MESTO_SEDISTA        int                  not null,
   OSNOVNA_DELATNOST    varchar(8)           null,
   PS_NAZIV             varchar(120)         not null,
   PS_ULICA             varchar(90)          null,
   PS_BROJ              varchar(14)          null,
   PS_WWW               varchar(40)          null,
   PS_E_MAIL            varchar(50)          null,
   PS_PAK               varchar(14)          null,
   PS_PIB               char(10)             null,
   PS_MB                char(7)              null,
   PS_REG               char(11)             null,
   PS_STATUS            char(1)              not null default 'E'
      constraint CKC_PS_STATUS_POSLOVNI check (PS_STATUS in ('E','L','S')),
   constraint PK_POSLOVNI_SISTEM primary key nonclustered (DRZAVA, VRSTA_SISTEMA, PS_ID)
)
go

/*==============================================================*/
/* Index: REGISTROVANI_POSLOVNI_SISTEMI_FK                      */
/*==============================================================*/
create index REGISTROVANI_POSLOVNI_SISTEMI_FK on POSLOVNI_SISTEM (
DRZAVA ASC
)
go

/*==============================================================*/
/* Index: SEDISTE_KOMPANIJE_FK                                  */
/*==============================================================*/
create index SEDISTE_KOMPANIJE_FK on POSLOVNI_SISTEM (
DRZAVA ASC,
MESTO_SEDISTA ASC
)
go

/*==============================================================*/
/* Index: OSNOVNA_DELATNOST_FK                                  */
/*==============================================================*/
create index OSNOVNA_DELATNOST_FK on POSLOVNI_SISTEM (
DRZAVA ASC,
OSNOVNA_DELATNOST ASC
)
go

/*==============================================================*/
/* Index: KLASIFIKACIJA_FK                                      */
/*==============================================================*/
create index KLASIFIKACIJA_FK on POSLOVNI_SISTEM (
VRSTA_SISTEMA ASC
)
go

/*==============================================================*/
/* Table: POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE                 */
/*==============================================================*/
create table POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   TIP_RESURSA          int                  not null,
   RESURS               int                  not null,
   TIP_USLUGE           int                  not null,
   USLUGA               int                  not null,
   constraint PK_POTREBNI_RESURSI_ZA_OBAVLJA primary key (DRZAVA, VRSTA_SISTEMA, POSLOVNI_SISTEM, TIP_RESURSA, TIP_USLUGE, RESURS, USLUGA)
)
go

/*==============================================================*/
/* Index: POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE_FK              */
/*==============================================================*/
create index POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE_FK on POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC,
TIP_RESURSA ASC,
RESURS ASC
)
go

/*==============================================================*/
/* Index: POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE2_FK             */
/*==============================================================*/
create index POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE2_FK on POTREBNI_RESURSI_ZA_OBAVLJANJE_USLUGE (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
TIP_USLUGE ASC,
POSLOVNI_SISTEM ASC,
USLUGA ASC
)
go

/*==============================================================*/
/* Table: PROIZVOD_ZA_SERVISIRANJE                              */
/*==============================================================*/
create table PROIZVOD_ZA_SERVISIRANJE (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   REGISTAR_KLIJENATA   int                  not null,
   TIP_KLIJENTA         int                  not null,
   KLIJENT              int                  not null,
   TIP_PROIZVODA        int                  not null,
   PROIZVOD             int                  not null,
   SERVISNA_ISTORIJA    int                  null,
   NAZIV_PROIZVODA      varchar(50)          not null,
   DATUM_PROIZVODNJE    datetime             not null,
   TRAJANJE_GARANCIJE   int                  not null,
   constraint PK_PROIZVOD_ZA_SERVISIRANJE primary key nonclustered (DRZAVA, VRSTA_SISTEMA, POSLOVNI_SISTEM, REGISTAR_KLIJENATA, TIP_KLIJENTA, KLIJENT, TIP_PROIZVODA, PROIZVOD)
)
go

/*==============================================================*/
/* Index: U_VLASNISTVU_FK                                       */
/*==============================================================*/
create index U_VLASNISTVU_FK on PROIZVOD_ZA_SERVISIRANJE (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC,
REGISTAR_KLIJENATA ASC,
TIP_KLIJENTA ASC,
KLIJENT ASC
)
go

/*==============================================================*/
/* Index: KLASIFIKACIJA_PROIZVODA_FK                            */
/*==============================================================*/
create index KLASIFIKACIJA_PROIZVODA_FK on PROIZVOD_ZA_SERVISIRANJE (
TIP_PROIZVODA ASC
)
go

/*==============================================================*/
/* Index: EVIDENCIJA_O_PROIZVODU_FK                             */
/*==============================================================*/
create index EVIDENCIJA_O_PROIZVODU_FK on PROIZVOD_ZA_SERVISIRANJE (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC,
REGISTAR_KLIJENATA ASC,
TIP_KLIJENTA ASC,
KLIJENT ASC,
TIP_PROIZVODA ASC,
PROIZVOD ASC,
SERVISNA_ISTORIJA ASC
)
go

/*==============================================================*/
/* Table: REGION                                                */
/*==============================================================*/
create table REGION (
   REG_IDENTIFIKATOR    int                  not null,
   DRZAVA               char(3)              null,
   DRZAVA_SEDISTA       char(3)              null,
   ADMINISTRATIVNO_SEDISTE int                  null,
   TIP_REGIONA          char(1)              not null,
   REG_NAZIV            varchar(80)          not null,
   constraint PK_REGION primary key nonclustered (REG_IDENTIFIKATOR)
)
go

/*==============================================================*/
/* Index: TIPIZACIJA_REGIONA_FK                                 */
/*==============================================================*/
create index TIPIZACIJA_REGIONA_FK on REGION (
TIP_REGIONA ASC
)
go

/*==============================================================*/
/* Index: MATICNA_DRZAVA_FK                                     */
/*==============================================================*/
create index MATICNA_DRZAVA_FK on REGION (
DRZAVA ASC
)
go

/*==============================================================*/
/* Index: ADMINISTRATIVNI_CENTAR_REGIONA_FK                     */
/*==============================================================*/
create index ADMINISTRATIVNI_CENTAR_REGIONA_FK on REGION (
DRZAVA_SEDISTA ASC,
ADMINISTRATIVNO_SEDISTE ASC
)
go

/*==============================================================*/
/* Table: REGISTAR_DELATNOSTI                                   */
/*==============================================================*/
create table REGISTAR_DELATNOSTI (
   DR_IDENTIFIKATOR     char(3)              not null,
   DEL_OZNAKA           varchar(8)           not null,
   DEL_NAZIV            varchar(80)          not null,
   constraint PK_REGISTAR_DELATNOSTI primary key nonclustered (DR_IDENTIFIKATOR, DEL_OZNAKA)
)
go

/*==============================================================*/
/* Index: REGISTROVANE_DELATNOSTI_FK                            */
/*==============================================================*/
create index REGISTROVANE_DELATNOSTI_FK on REGISTAR_DELATNOSTI (
DR_IDENTIFIKATOR ASC
)
go

/*==============================================================*/
/* Table: REGISTAR_KLIJENATA                                    */
/*==============================================================*/
create table REGISTAR_KLIJENATA (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   REGISTAR_KLIJENATA   int                  not null,
   NAZIV                varchar(50)          not null,
   constraint PK_REGISTAR_KLIJENATA primary key nonclustered (DRZAVA, VRSTA_SISTEMA, POSLOVNI_SISTEM, REGISTAR_KLIJENATA)
)
go

/*==============================================================*/
/* Index: SADRZI_REGISTRE_FK                                    */
/*==============================================================*/
create index SADRZI_REGISTRE_FK on REGISTAR_KLIJENATA (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC
)
go

/*==============================================================*/
/* Table: REGISTROVAN_ZA                                        */
/*==============================================================*/
create table REGISTROVAN_ZA (
   DRZAVA               char(3)              not null,
   DELATNOST            varchar(8)           not null,
   VRSTA_POSLOVNOG_SISTEMA char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   constraint PK_REGISTROVAN_ZA primary key (DRZAVA, DELATNOST, VRSTA_POSLOVNOG_SISTEMA, POSLOVNI_SISTEM)
)
go

/*==============================================================*/
/* Index: REGISTROVAN_ZA_FK                                     */
/*==============================================================*/
create index REGISTROVAN_ZA_FK on REGISTROVAN_ZA (
DRZAVA ASC,
DELATNOST ASC
)
go

/*==============================================================*/
/* Index: REGISTROVAN_ZA2_FK                                    */
/*==============================================================*/
create index REGISTROVAN_ZA2_FK on REGISTROVAN_ZA (
DRZAVA ASC,
VRSTA_POSLOVNOG_SISTEMA ASC,
POSLOVNI_SISTEM ASC
)
go

/*==============================================================*/
/* Table: RESURSI_SERVISNE_STANICE                              */
/*==============================================================*/
create table RESURSI_SERVISNE_STANICE (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   TIP_RESURSA          int                  not null,
   RESURS               int                  not null,
   NAZIV_RESURSA        varchar(50)          not null,
   KOLICINA             int                  not null,
   constraint PK_RESURSI_SERVISNE_STANICE primary key nonclustered (DRZAVA, VRSTA_SISTEMA, POSLOVNI_SISTEM, TIP_RESURSA, RESURS)
)
go

/*==============================================================*/
/* Index: RESURSI_NA_RASPOLAGANJU_FK                            */
/*==============================================================*/
create index RESURSI_NA_RASPOLAGANJU_FK on RESURSI_SERVISNE_STANICE (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC
)
go

/*==============================================================*/
/* Index: KLASIFIKACIJA_RESURSA_FK                              */
/*==============================================================*/
create index KLASIFIKACIJA_RESURSA_FK on RESURSI_SERVISNE_STANICE (
TIP_RESURSA ASC
)
go

/*==============================================================*/
/* Table: REZERVACIJA                                           */
/*==============================================================*/
create table REZERVACIJA (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   REZERVACIJA          int                  not null,
   REGISTAR_KLIJENATA   int                  not null,
   TIP_KLIJENTA         int                  not null,
   KLIJENT              int                  not null,
   DATUM_I_VREME        datetime             not null,
   constraint PK_REZERVACIJA primary key nonclustered (DRZAVA, VRSTA_SISTEMA, POSLOVNI_SISTEM, REZERVACIJA)
)
go

/*==============================================================*/
/* Index: REZERVACIJA_TERMINA_ZA_OBAVLJANJE_SERVISIRA_FK        */
/*==============================================================*/
create index REZERVACIJA_TERMINA_ZA_OBAVLJANJE_SERVISIRA_FK on REZERVACIJA (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC
)
go

/*==============================================================*/
/* Index: ZA_KOG_KLIJENTA_FK                                    */
/*==============================================================*/
create index ZA_KOG_KLIJENTA_FK on REZERVACIJA (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC,
REGISTAR_KLIJENATA ASC,
TIP_KLIJENTA ASC,
KLIJENT ASC
)
go

/*==============================================================*/
/* Table: SASTAV_DRZAVE                                         */
/*==============================================================*/
create table SASTAV_DRZAVE (
   DRZAVA               char(3)              not null,
   DRZAVA_UNUTAR        char(3)              not null,
   constraint PK_SASTAV_DRZAVE primary key (DRZAVA, DRZAVA_UNUTAR)
)
go

/*==============================================================*/
/* Index: SASTAV_DRZAVE_FK                                      */
/*==============================================================*/
create index SASTAV_DRZAVE_FK on SASTAV_DRZAVE (
DRZAVA ASC
)
go

/*==============================================================*/
/* Index: SASTAV_DRZAVE2_FK                                     */
/*==============================================================*/
create index SASTAV_DRZAVE2_FK on SASTAV_DRZAVE (
DRZAVA_UNUTAR ASC
)
go

/*==============================================================*/
/* Table: SASTAV_NASELJA                                        */
/*==============================================================*/
create table SASTAV_NASELJA (
   DRZAVA_SLOZENOG_MESTA char(3)              not null,
   SLOZENO_MESTO        int                  not null,
   MESTO_U_SASTAVU      int                  not null,
   constraint PK_SASTAV_NASELJA primary key (DRZAVA_SLOZENOG_MESTA, SLOZENO_MESTO, MESTO_U_SASTAVU)
)
go

/*==============================================================*/
/* Index: SASTAV_NASELJA_FK                                     */
/*==============================================================*/
create index SASTAV_NASELJA_FK on SASTAV_NASELJA (
DRZAVA_SLOZENOG_MESTA ASC,
SLOZENO_MESTO ASC
)
go

/*==============================================================*/
/* Index: SASTAV_NASELJA2_FK                                    */
/*==============================================================*/
create index SASTAV_NASELJA2_FK on SASTAV_NASELJA (
DRZAVA_SLOZENOG_MESTA ASC,
MESTO_U_SASTAVU ASC
)
go

/*==============================================================*/
/* Table: SASTAV_ORGANIZACIONE_JEDINICE                         */
/*==============================================================*/
create table SASTAV_ORGANIZACIONE_JEDINICE (
   DRZAVA_SLOZENE       char(3)              not null,
   VRSTA_SISTEMA_SLOZENE char(2)              not null,
   POSLOVNI_SISTEM_SLOZENE int                  not null,
   VRSTA_SLOZENE_JEDINICE char(2)              not null,
   SLOZENA_JEDINICA     int                  not null,
   VRSTA_SADRZANE_JEDINICE char(2)              not null,
   SADRZANA_JEDINICA    int                  not null,
   constraint PK_SASTAV_ORGANIZACIONE_JEDINI primary key (DRZAVA_SLOZENE, VRSTA_SISTEMA_SLOZENE, POSLOVNI_SISTEM_SLOZENE, VRSTA_SADRZANE_JEDINICE, VRSTA_SLOZENE_JEDINICE, SLOZENA_JEDINICA, SADRZANA_JEDINICA)
)
go

/*==============================================================*/
/* Index: SASTAV_ORGANIZACIONE_JEDINICE_FK                      */
/*==============================================================*/
create index SASTAV_ORGANIZACIONE_JEDINICE_FK on SASTAV_ORGANIZACIONE_JEDINICE (
DRZAVA_SLOZENE ASC,
VRSTA_SISTEMA_SLOZENE ASC,
POSLOVNI_SISTEM_SLOZENE ASC,
VRSTA_SLOZENE_JEDINICE ASC,
SLOZENA_JEDINICA ASC
)
go

/*==============================================================*/
/* Index: SASTAV_ORGANIZACIONE_JEDINICE2_FK                     */
/*==============================================================*/
create index SASTAV_ORGANIZACIONE_JEDINICE2_FK on SASTAV_ORGANIZACIONE_JEDINICE (
DRZAVA_SLOZENE ASC,
VRSTA_SISTEMA_SLOZENE ASC,
POSLOVNI_SISTEM_SLOZENE ASC,
VRSTA_SADRZANE_JEDINICE ASC,
SADRZANA_JEDINICA ASC
)
go

/*==============================================================*/
/* Table: SASTAV_POSLOVNOG_SISTEMA                              */
/*==============================================================*/
create table SASTAV_POSLOVNOG_SISTEMA (
   DRZAVA_SLOZENOG      char(3)              not null,
   VRSTA_SLOZENOG       char(2)              not null,
   SLOZENI_SISTEM       int                  not null,
   DRZAVA_SADRZANOG     char(3)              not null,
   VRSTA_SADRZANOG      char(2)              not null,
   SADRZANI_SISTEM      int                  not null,
   constraint PK_SASTAV_POSLOVNOG_SISTEMA primary key (DRZAVA_SADRZANOG, DRZAVA_SLOZENOG, VRSTA_SLOZENOG, SLOZENI_SISTEM, VRSTA_SADRZANOG, SADRZANI_SISTEM)
)
go

/*==============================================================*/
/* Index: SASTAV_POSLOVNOG_SISTEMA_FK                           */
/*==============================================================*/
create index SASTAV_POSLOVNOG_SISTEMA_FK on SASTAV_POSLOVNOG_SISTEMA (
DRZAVA_SLOZENOG ASC,
VRSTA_SLOZENOG ASC,
SLOZENI_SISTEM ASC
)
go

/*==============================================================*/
/* Index: SASTAV_POSLOVNOG_SISTEMA2_FK                          */
/*==============================================================*/
create index SASTAV_POSLOVNOG_SISTEMA2_FK on SASTAV_POSLOVNOG_SISTEMA (
DRZAVA_SADRZANOG ASC,
VRSTA_SADRZANOG ASC,
SADRZANI_SISTEM ASC
)
go

/*==============================================================*/
/* Table: SASTAV_REGIONA                                        */
/*==============================================================*/
create table SASTAV_REGIONA (
   SLOZENI_REGION       int                  not null,
   REGION_U_SASTAVU     int                  not null,
   constraint PK_SASTAV_REGIONA primary key (SLOZENI_REGION, REGION_U_SASTAVU)
)
go

/*==============================================================*/
/* Index: SASTAV_REGIONA_FK                                     */
/*==============================================================*/
create index SASTAV_REGIONA_FK on SASTAV_REGIONA (
SLOZENI_REGION ASC
)
go

/*==============================================================*/
/* Index: SASTAV_REGIONA2_FK                                    */
/*==============================================================*/
create index SASTAV_REGIONA2_FK on SASTAV_REGIONA (
REGION_U_SASTAVU ASC
)
go

/*==============================================================*/
/* Table: SERVISNA_ISTORIJA                                     */
/*==============================================================*/
create table SERVISNA_ISTORIJA (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   REGISTAR_KLIJENATA   int                  not null,
   TIP_KLIJENTA         int                  not null,
   KLIJENT              int                  not null,
   TIP_PROIZVODA        int                  not null,
   PROIZVOD             int                  not null,
   SERVISNA_ISTORIJA    int                  not null,
   NAZIV                varchar(50)          not null,
   constraint PK_SERVISNA_ISTORIJA primary key nonclustered (DRZAVA, VRSTA_SISTEMA, POSLOVNI_SISTEM, REGISTAR_KLIJENATA, TIP_KLIJENTA, KLIJENT, TIP_PROIZVODA, PROIZVOD, SERVISNA_ISTORIJA)
)
go

/*==============================================================*/
/* Index: EVIDENCIJA_O_PROIZVODU2_FK                            */
/*==============================================================*/
create index EVIDENCIJA_O_PROIZVODU2_FK on SERVISNA_ISTORIJA (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC,
REGISTAR_KLIJENATA ASC,
TIP_KLIJENTA ASC,
KLIJENT ASC,
TIP_PROIZVODA ASC,
PROIZVOD ASC
)
go

/*==============================================================*/
/* Table: SERVISNA_STANICA                                      */
/*==============================================================*/
create table SERVISNA_STANICA (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   PS_ID                int                  not null,
   MESTO_SEDISTA        int                  null,
   OSNOVNA_DELATNOST    varchar(8)           null,
   PS_NAZIV             varchar(120)         not null,
   PS_ULICA             varchar(90)          null,
   PS_BROJ              varchar(14)          null,
   PS_WWW               varchar(40)          null,
   PS_E_MAIL            varchar(50)          null,
   PS_PAK               varchar(14)          null,
   PS_PIB               char(10)             null,
   PS_MB                char(7)              null,
   PS_REG               char(11)             null,
   PS_STATUS            char(1)              not null default 'E'
      constraint CKC_PS_STATUS_SERVISNA check (PS_STATUS in ('E','L','S')),
   constraint PK_SERVISNA_STANICA primary key (DRZAVA, VRSTA_SISTEMA, PS_ID)
)
go

/*==============================================================*/
/* Table: TIP_CENOVNIKA                                         */
/*==============================================================*/
create table TIP_CENOVNIKA (
   TIP_CENOVNIKA_ID     int                  not null,
   NAZIV                varchar(50)          not null,
   constraint PK_TIP_CENOVNIKA primary key nonclustered (TIP_CENOVNIKA_ID)
)
go

/*==============================================================*/
/* Table: TIP_KLIJENTA                                          */
/*==============================================================*/
create table TIP_KLIJENTA (
   TIP_KLIJENTA_ID      int                  not null,
   NAZIV                varchar(50)          not null,
   constraint PK_TIP_KLIJENTA primary key nonclustered (TIP_KLIJENTA_ID)
)
go

/*==============================================================*/
/* Table: TIP_PROIZVODA                                         */
/*==============================================================*/
create table TIP_PROIZVODA (
   TIP_PROIZVODA_ID     int                  not null,
   NAZIV                varchar(50)          not null,
   constraint PK_TIP_PROIZVODA primary key nonclustered (TIP_PROIZVODA_ID)
)
go

/*==============================================================*/
/* Table: TIP_RADNOG_MESTA                                      */
/*==============================================================*/
create table TIP_RADNOG_MESTA (
   RADNO_MESTO_ID       int                  not null,
   NAZIV                varchar(50)          not null,
   constraint PK_TIP_RADNOG_MESTA primary key nonclustered (RADNO_MESTO_ID)
)
go

/*==============================================================*/
/* Table: TIP_REGIONA                                           */
/*==============================================================*/
create table TIP_REGIONA (
   TR_OZNAKA            char(1)              not null,
   TR_NAZIV             varchar(40)          not null,
   constraint PK_TIP_REGIONA primary key nonclustered (TR_OZNAKA)
)
go

/*==============================================================*/
/* Table: TIP_RESURSA                                           */
/*==============================================================*/
create table TIP_RESURSA (
   TIP_RESURSA          int                  not null,
   NAZIV                varchar(50)          not null,
   constraint PK_TIP_RESURSA primary key nonclustered (TIP_RESURSA)
)
go

/*==============================================================*/
/* Table: TIP_USLUGE                                            */
/*==============================================================*/
create table TIP_USLUGE (
   TIP_USLUGE_ID        int                  not null,
   NAZIV                varchar(50)          not null,
   constraint PK_TIP_USLUGE primary key nonclustered (TIP_USLUGE_ID)
)
go

/*==============================================================*/
/* Table: UKLJUCUJE_MESTA                                       */
/*==============================================================*/
create table UKLJUCUJE_MESTA (
   REGION               int                  not null,
   DRZAVA_NASELJENOG_MESTA char(3)              not null,
   NASELJENO_MESTO      int                  not null,
   constraint PK_UKLJUCUJE_MESTA primary key (DRZAVA_NASELJENOG_MESTA, REGION, NASELJENO_MESTO)
)
go

/*==============================================================*/
/* Index: UKLJUCUJE_MESTA_FK                                    */
/*==============================================================*/
create index UKLJUCUJE_MESTA_FK on UKLJUCUJE_MESTA (
REGION ASC
)
go

/*==============================================================*/
/* Index: UKLJUCUJE_MESTA2_FK                                   */
/*==============================================================*/
create index UKLJUCUJE_MESTA2_FK on UKLJUCUJE_MESTA (
DRZAVA_NASELJENOG_MESTA ASC,
NASELJENO_MESTO ASC
)
go

/*==============================================================*/
/* Table: UKLJUCUJE_OPSTINE                                     */
/*==============================================================*/
create table UKLJUCUJE_OPSTINE (
   REGION               int                  not null,
   DRZAVA_OPSTINE       char(3)              not null,
   OPSTINA              numeric(5,0)         not null,
   constraint PK_UKLJUCUJE_OPSTINE primary key (DRZAVA_OPSTINE, REGION, OPSTINA)
)
go

/*==============================================================*/
/* Index: UKLJUCUJE_OPSTINE_FK                                  */
/*==============================================================*/
create index UKLJUCUJE_OPSTINE_FK on UKLJUCUJE_OPSTINE (
REGION ASC
)
go

/*==============================================================*/
/* Index: UKLJUCUJE_OPSTINE2_FK                                 */
/*==============================================================*/
create index UKLJUCUJE_OPSTINE2_FK on UKLJUCUJE_OPSTINE (
DRZAVA_OPSTINE ASC,
OPSTINA ASC
)
go

/*==============================================================*/
/* Table: USLUGE                                                */
/*==============================================================*/
create table USLUGE (
   TIP_USLUGE           int                  not null,
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   USLUGA               int                  not null,
   NAZIV                varchar(50)          not null,
   constraint PK_USLUGE primary key nonclustered (DRZAVA, VRSTA_SISTEMA, TIP_USLUGE, POSLOVNI_SISTEM, USLUGA)
)
go

/*==============================================================*/
/* Index: KLASIFIKACIJA_USLUGE_FK                               */
/*==============================================================*/
create index KLASIFIKACIJA_USLUGE_FK on USLUGE (
TIP_USLUGE ASC
)
go

/*==============================================================*/
/* Index: PRUZA_FK                                              */
/*==============================================================*/
create index PRUZA_FK on USLUGE (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC
)
go

/*==============================================================*/
/* Table: USLUGE_UNUTAR_CENOVNIKA                               */
/*==============================================================*/
create table USLUGE_UNUTAR_CENOVNIKA (
   DRZAVA               char(3)              not null,
   POSLOVNI_SISTEM      int                  not null,
   TIP_CENOVNIKA        int                  not null,
   CENOVNIK             int                  not null,
   VRSTA_SISTEMA        char(2)              not null,
   TIP_USLUGE           int                  not null,
   USLUGA               int                  not null,
   constraint PK_USLUGE_UNUTAR_CENOVNIKA primary key (DRZAVA, VRSTA_SISTEMA, TIP_USLUGE, POSLOVNI_SISTEM, TIP_CENOVNIKA, CENOVNIK, USLUGA)
)
go

/*==============================================================*/
/* Index: USLUGE_UNUTAR_CENOVNIKA_FK                            */
/*==============================================================*/
create index USLUGE_UNUTAR_CENOVNIKA_FK on USLUGE_UNUTAR_CENOVNIKA (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC,
TIP_CENOVNIKA ASC,
CENOVNIK ASC
)
go

/*==============================================================*/
/* Index: USLUGE_UNUTAR_CENOVNIKA2_FK                           */
/*==============================================================*/
create index USLUGE_UNUTAR_CENOVNIKA2_FK on USLUGE_UNUTAR_CENOVNIKA (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
TIP_USLUGE ASC,
POSLOVNI_SISTEM ASC,
USLUGA ASC
)
go

/*==============================================================*/
/* Table: VRSTA_ORGANIZACIONE_JEDINICE                          */
/*==============================================================*/
create table VRSTA_ORGANIZACIONE_JEDINICE (
   VO_OZNAKA            char(2)              not null,
   VO_NAZIV             varchar(50)          not null,
   constraint PK_VRSTA_ORGANIZACIONE_JEDINIC primary key nonclustered (VO_OZNAKA)
)
go

/*==============================================================*/
/* Table: VRSTA_PARTNERSTVA                                     */
/*==============================================================*/
create table VRSTA_PARTNERSTVA (
   VP_OZNAKA            char(1)              not null,
   VP_NAZIV             varchar(50)          not null,
   constraint PK_VRSTA_PARTNERSTVA primary key nonclustered (VP_OZNAKA)
)
go

/*==============================================================*/
/* Table: VRSTA_POSLOVNOG_SISTEMA                               */
/*==============================================================*/
create table VRSTA_POSLOVNOG_SISTEMA (
   VPS_IDENTIFIKATOE    char(2)              not null,
   VPS_NAZIV            varchar(50)          not null,
   constraint PK_VRSTA_POSLOVNOG_SISTEMA primary key nonclustered (VPS_IDENTIFIKATOE)
)
go

/*==============================================================*/
/* Table: ZAPOSLENI                                             */
/*==============================================================*/
create table ZAPOSLENI (
   DRZAVA_SISTEMA       char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   RADNO_MESTO          int                  not null,
   ZAPOSLEN             int                  not null,
   IME                  varchar(50)          not null,
   PREZIME              varchar(50)          not null,
   constraint PK_ZAPOSLENI primary key nonclustered (DRZAVA_SISTEMA, VRSTA_SISTEMA, POSLOVNI_SISTEM, RADNO_MESTO, ZAPOSLEN)
)
go

/*==============================================================*/
/* Index: ZAPOSLENI_U_FK                                        */
/*==============================================================*/
create index ZAPOSLENI_U_FK on ZAPOSLENI (
DRZAVA_SISTEMA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC
)
go

/*==============================================================*/
/* Index: KLASIFIKACIJA_RADNOG_MESTA_FK                         */
/*==============================================================*/
create index KLASIFIKACIJA_RADNOG_MESTA_FK on ZAPOSLENI (
RADNO_MESTO ASC
)
go

/*==============================================================*/
/* Table: ZA_KOJE_USLUGE                                        */
/*==============================================================*/
create table ZA_KOJE_USLUGE (
   DRZAVA               char(3)              not null,
   VRSTA_SISTEMA        char(2)              not null,
   POSLOVNI_SISTEM      int                  not null,
   REZERVACIJA          int                  not null,
   TIP_USLUGE           int                  not null,
   USLUGA               int                  not null,
   constraint PK_ZA_KOJE_USLUGE primary key (DRZAVA, VRSTA_SISTEMA, TIP_USLUGE, POSLOVNI_SISTEM, REZERVACIJA, USLUGA)
)
go

/*==============================================================*/
/* Index: ZA_KOJE_USLUGE_FK                                     */
/*==============================================================*/
create index ZA_KOJE_USLUGE_FK on ZA_KOJE_USLUGE (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
POSLOVNI_SISTEM ASC,
REZERVACIJA ASC
)
go

/*==============================================================*/
/* Index: ZA_KOJE_USLUGE2_FK                                    */
/*==============================================================*/
create index ZA_KOJE_USLUGE2_FK on ZA_KOJE_USLUGE (
DRZAVA ASC,
VRSTA_SISTEMA ASC,
TIP_USLUGE ASC,
POSLOVNI_SISTEM ASC,
USLUGA ASC
)
go

