package fthink.dao;

import java.util.List;

import fthink.entity.Term;


public interface TermMapper extends BaseMapper {

	List<Term> selectTermInfo();
}