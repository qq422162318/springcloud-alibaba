package com.my.dao;

import com.my.domain.Order;
import com.my.domain.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TxLogDao extends JpaRepository<TxLog,String> {

}
