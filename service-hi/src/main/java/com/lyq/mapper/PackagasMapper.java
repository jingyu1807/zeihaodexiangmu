package com.lyq.mapper;

import com.lyq.model.Packages;

import java.util.List;

public interface PackagasMapper {


    List<Packages> queryPackages();

    List<Packages> queryChoice(Integer id);
}
