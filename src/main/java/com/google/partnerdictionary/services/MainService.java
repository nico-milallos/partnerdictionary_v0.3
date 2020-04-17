package com.google.partnerdictionary.services;

import java.util.List;

public interface MainService<T1, T2> {

  List<T1> getAll();

  T1 getById(Integer id);

  T1 saveOrUpdate(T1 var);

  void delete(Integer id);

  T1 saveOrUpdateForm(T2 var);

}
