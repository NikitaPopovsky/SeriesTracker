package com.github.NikitaPopovskiy.SeriesTracker.repositories;


import com.github.NikitaPopovskiy.SeriesTracker.models.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface SerialsRepository extends CrudRepository <Serial, Long>  {

    List<Serial> findSerialByIdSerialTmdb(int idTmdb);
}
