package com.kapil.employeemanagement.retrofit.network;

public interface EmpApiListener<T, E> {

  void onSuccess(T object);

  void onError(E object);

}