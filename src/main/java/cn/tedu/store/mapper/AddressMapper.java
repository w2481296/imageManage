package cn.tedu.store.mapper;

import java.util.List;


public interface AddressMapper {
	Integer updateByUid(Integer uid);
	Integer updateById(Integer id);
	void deleteById(Integer id);
}
