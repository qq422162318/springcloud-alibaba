package com.my.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity(name = "shop_txlog")
@Data
public class TxLog {
    @Id
    private String txid;
    private Date date;
}
