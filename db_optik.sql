# Host: localhost  (Version 5.5.5-10.1.34-MariaDB)
# Date: 2018-08-10 21:01:28
# Generator: MySQL-Front 5.3  (Build 8.0)

/*!40101 SET NAMES latin1 */;

#
# Structure for table "buktipembelian"
#

DROP TABLE IF EXISTS `buktipembelian`;
CREATE TABLE `buktipembelian` (
  `no_bp` varchar(6) NOT NULL DEFAULT '',
  `tgl_bp` date DEFAULT NULL,
  `kd_supp` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`no_bp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "buktipembelian"
#

INSERT INTO `buktipembelian` VALUES ('BP0001','2018-07-19','SP001');

#
# Structure for table "frame"
#

DROP TABLE IF EXISTS `frame`;
CREATE TABLE `frame` (
  `kd_frame` varchar(5) NOT NULL DEFAULT '',
  `nm_frame` varchar(20) DEFAULT NULL,
  `hrg_beli_satuan` int(7) DEFAULT NULL,
  `hrg_jual_frame` int(7) DEFAULT NULL,
  `stok_frame` int(3) DEFAULT NULL,
  PRIMARY KEY (`kd_frame`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "frame"
#

INSERT INTO `frame` VALUES ('FM001','Molsion',400000,600000,-3),('FM002','Hong Ten',0,0,-1),('FM003','Illustro',0,0,0);

#
# Structure for table "isipembelianframe"
#

DROP TABLE IF EXISTS `isipembelianframe`;
CREATE TABLE `isipembelianframe` (
  `no_bp` varchar(6) NOT NULL DEFAULT '',
  `kd_frame` varchar(5) NOT NULL DEFAULT '',
  `hrg_beli_satuan` int(7) DEFAULT NULL,
  `qty` int(3) DEFAULT NULL,
  PRIMARY KEY (`no_bp`,`kd_frame`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "isipembelianframe"
#

INSERT INTO `isipembelianframe` VALUES ('BP0001','FM001',400000,10);

#
# Structure for table "isipembelianlensa"
#

DROP TABLE IF EXISTS `isipembelianlensa`;
CREATE TABLE `isipembelianlensa` (
  `no_bp` varchar(6) NOT NULL DEFAULT '',
  `kd_lensa` varchar(5) NOT NULL DEFAULT '',
  `hrg_beli_satuan` int(7) DEFAULT NULL,
  `qty` int(3) DEFAULT NULL,
  PRIMARY KEY (`no_bp`,`kd_lensa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "isipembelianlensa"
#

INSERT INTO `isipembelianlensa` VALUES ('BP0001','LS003',200000,11);

#
# Structure for table "isipenjualanframe"
#

DROP TABLE IF EXISTS `isipenjualanframe`;
CREATE TABLE `isipenjualanframe` (
  `no_nota` varchar(6) NOT NULL DEFAULT '',
  `kd_frame` varchar(5) NOT NULL DEFAULT '',
  `qty` int(3) DEFAULT NULL,
  `hrg_frame` int(7) DEFAULT NULL,
  PRIMARY KEY (`no_nota`,`kd_frame`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "isipenjualanframe"
#

INSERT INTO `isipenjualanframe` VALUES ('NO0001','FM005',2,15000),('NO0002','FM008',2,6000),('NO0003','FM005',6,15000),('NO0004','FM008',2,6000),('NO0005','FM005',2,15000),('NO0006','FM008',2,6000),('NO0007','FM008',1,6000),('NO0008','FM001',1,600000),('NO0009','FM001',1,600000),('NO0010','FM002',1,75000),('NO0011','FM001',10,600000),('NO0012','FM001',1,600000);

#
# Structure for table "isipenjualanlensa"
#

DROP TABLE IF EXISTS `isipenjualanlensa`;
CREATE TABLE `isipenjualanlensa` (
  `no_nota` varchar(6) NOT NULL DEFAULT '',
  `kd_lensa` varchar(5) NOT NULL DEFAULT '',
  `qty` int(3) DEFAULT NULL,
  `hrg_lensa` int(7) DEFAULT NULL,
  PRIMARY KEY (`no_nota`,`kd_lensa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "isipenjualanlensa"
#

INSERT INTO `isipenjualanlensa` VALUES ('NO0001','LS002',2,30000),('NO0002','LS002',3,30000),('NO0003','LS002',5,30000),('NO0004','LS002',3,30000),('NO0005','LS002',11,30000),('NO0006','LS002',10,30000),('NO0007','LS002',1,30000),('NO0008','LS003',1,300000),('NO0009','LS003',1,300000),('NO0010','LS002',1,5000),('NO0011','LS003',10,300000),('NO0012','LS003',1,300000);

#
# Structure for table "kartuperiksa"
#

DROP TABLE IF EXISTS `kartuperiksa`;
CREATE TABLE `kartuperiksa` (
  `no_kartuperiksa` varchar(6) NOT NULL DEFAULT '',
  `no_resep` varchar(6) DEFAULT NULL,
  `tgl_periksa` date DEFAULT NULL,
  PRIMARY KEY (`no_kartuperiksa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "kartuperiksa"
#

INSERT INTO `kartuperiksa` VALUES ('KP0001','RS0001','2018-07-19'),('KP0002','RS0003','2018-07-19'),('KP0003','RS0001','2018-07-19'),('KP0004','RS0002','2018-07-19'),('KP0005','RS0004','2018-07-24');

#
# Structure for table "kwitansi"
#

DROP TABLE IF EXISTS `kwitansi`;
CREATE TABLE `kwitansi` (
  `no_kwitansi` varchar(6) NOT NULL DEFAULT '',
  `tgl_kwitansi` date DEFAULT NULL,
  `dp` double(7,0) DEFAULT NULL,
  `sisa_pembayaran` double(7,0) DEFAULT NULL,
  `jml_lunas` double(7,0) DEFAULT NULL,
  `no_nota` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`no_kwitansi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "kwitansi"
#

INSERT INTO `kwitansi` VALUES ('KW0001','2018-07-18',20000,82000,82000,'NO0004'),('KW0002','2018-07-18',300000,12000,12000,'NO0006'),('KW0003','2018-07-19',20000,16000,16000,'NO0007'),('KW0004','2018-07-19',200000,700000,700000,'NO0008'),('KW0005','2018-07-19',1000000,-100000,2000000,'NO0009'),('KW0006','2018-07-24',100000,800000,800000,'NO0012');

#
# Structure for table "lensa"
#

DROP TABLE IF EXISTS `lensa`;
CREATE TABLE `lensa` (
  `kd_lensa` varchar(5) NOT NULL DEFAULT '',
  `nm_lensa` varchar(20) DEFAULT NULL,
  `hrg_beli_satuan` int(7) DEFAULT NULL,
  `hrg_jual_lensa` int(7) DEFAULT NULL,
  `stok_lensa` int(3) DEFAULT NULL,
  PRIMARY KEY (`kd_lensa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "lensa"
#

INSERT INTO `lensa` VALUES ('LS001','Single Vision',0,0,0),('LS002','Bifokal',0,0,-1),('LS003','Progressive',200000,300000,-2);

#
# Structure for table "nota"
#

DROP TABLE IF EXISTS `nota`;
CREATE TABLE `nota` (
  `no_nota` varchar(6) NOT NULL,
  `tgl_nota` date DEFAULT NULL,
  `total_harga` double(7,0) DEFAULT NULL,
  `dp` double(7,0) DEFAULT NULL,
  `sisa_pembayaran` double(7,0) DEFAULT NULL,
  `no_resep` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`no_nota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "nota"
#

INSERT INTO `nota` VALUES ('NO0001','2018-07-18',90000,20000,70000,'RP0001'),('NO0002','2018-07-18',102000,100000,2000,'RP0001'),('NO0003','2018-07-18',240000,100000,140000,'RP0001'),('NO0004','2018-07-18',102000,20000,82000,'RP0001'),('NO0005','2018-07-18',360000,20000,340000,'RP0001'),('NO0006','2018-07-18',312000,300000,12000,'RP0001'),('NO0007','2018-07-19',36000,20000,16000,'RS0001'),('NO0008','2018-07-19',900000,200000,700000,'RS0002'),('NO0009','2018-07-19',900000,1000000,-100000,'RS0003'),('NO0010','2018-07-19',80000,50000,30000,'RS0003'),('NO0011','2018-07-19',9000000,100000,8900000,'RS0001'),('NO0012','2018-07-24',900000,100000,800000,'RS0004');

#
# Structure for table "pelanggan"
#

DROP TABLE IF EXISTS `pelanggan`;
CREATE TABLE `pelanggan` (
  `kd_pelanggan` varchar(5) NOT NULL DEFAULT '',
  `nm_pelanggan` varchar(30) DEFAULT NULL,
  `alamat` varchar(25) DEFAULT NULL,
  `umur` varchar(3) DEFAULT NULL,
  `telp` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`kd_pelanggan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "pelanggan"
#

INSERT INTO `pelanggan` VALUES ('PG001','Muhammad Ivan Maulana','Muhammad Ivan Maulana','20','081288090459'),('PG002','Sandy Putra utama','Sandy Putra utama','20','089656499692');

#
# Structure for table "resep"
#

DROP TABLE IF EXISTS `resep`;
CREATE TABLE `resep` (
  `no_resep` varchar(6) NOT NULL DEFAULT '',
  `tgl_pesan` date DEFAULT NULL,
  `tgl_ambil` date DEFAULT NULL,
  `od_sph` float(3,2) DEFAULT NULL,
  `od_cyl` float(3,2) DEFAULT NULL,
  `od_axis` int(3) DEFAULT NULL,
  `os_sph` float(3,2) DEFAULT NULL,
  `os_cyl` float(3,2) DEFAULT NULL,
  `os_axis` int(3) DEFAULT NULL,
  `add_resep` float(3,2) DEFAULT NULL,
  `pd_resep` int(3) DEFAULT NULL,
  `kd_pelanggan` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`no_resep`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "resep"
#

INSERT INTO `resep` VALUES ('RS0001','2018-07-19','2018-07-20',-1.00,-1.00,100,-1.00,-1.00,100,0.00,66,'PG001'),('RS0002','2018-07-19','2018-07-20',-1.00,-1.00,90,-1.00,-1.00,180,0.00,66,'PG001'),('RS0003','2018-07-19','2018-07-20',-1.00,-1.00,180,-1.00,-1.00,180,0.00,66,'PG002'),('RS0004','2018-07-24','0208-07-26',-2.00,0.00,100,-2.00,0.00,100,0.00,66,'PG002');

#
# Structure for table "supplier"
#

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `kd_supp` varchar(5) NOT NULL DEFAULT '',
  `nm_supp` varchar(50) DEFAULT NULL,
  `tlpn_supp` varchar(12) DEFAULT NULL,
  `alamat_supp` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`kd_supp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "supplier"
#

INSERT INTO `supplier` VALUES ('SP001','lanjar','0812009890','l.cipete raya');
