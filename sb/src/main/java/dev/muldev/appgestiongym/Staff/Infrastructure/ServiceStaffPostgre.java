/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.staff.infrastructure;

import dev.muldev.appgestiongym.staff.domain.ServiceStaff;
import dev.muldev.appgestiongym.staff.domain.Staff;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public final class ServiceStaffPostgre implements ServiceStaff {
    
    private final StaffRepository repo;
    private final EntityManager em;

    public ServiceStaffPostgre(StaffRepository repo, EntityManager em) {
        this.repo = repo;
        this.em = em;
    }
    
    

    @Override
    public Staff addStaff(Staff staff) {
        //mapping
        ModelMapper modelMapper = new ModelMapper();
        StaffEntity p = modelMapper.map(staff, StaffEntity.class);
        p = repo.save(p);
        return modelMapper.map(p, Staff.class);
    }

    @Override
    public Staff getOne(int id) {
        try{
            StaffEntity p = repo.getOne(id);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(p, Staff.class);
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public Double calculateSalaries() {
        Double total = 0.0;
        try{
            Query q = em.createNamedQuery("PersonalGym.sumaSueldos");
            
            Double totalTemporal = Double.parseDouble(q.getSingleResult().toString());
            
            //taex
            total = totalTemporal * 1.236; //seg social
            total = total + (totalTemporal * 0.055); // desempleo
            total = total + (totalTemporal * 0.02); // FOGASA
            total = total + (totalTemporal * 0.07); // Formacion profesional

            return total;
        }
        catch(NoResultException e){
            return 0.0;
        }
    }

    @Override
    public List<Staff> listStaff() {
        
        List<StaffEntity> lista = repo.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<Staff> listDomain = new ArrayList<>();
        
        for(StaffEntity p: lista){
            Staff staff = modelMapper.map(p, Staff.class);
            listDomain.add(staff);
        }
        
        return listDomain;
    }

    @Override
    public boolean delById(int id) {
        try{
            repo.deleteById(id);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Staff getByUsername(String username) {
        try {
            Query q = em.createNamedQuery("PersonalGym.findByNifPersonal").setParameter("nifPersonal", username);
            StaffEntity entity = (StaffEntity) q.getSingleResult();
            ModelMapper modelMapper = new ModelMapper();
            Staff p = modelMapper.map(entity, Staff.class);
            return p;
        }
        catch(NoResultException e){
            return null;
        }
            

    }

    @Override
    public List<Staff> getStaffWithRole(String role) {
        try {
            Query q = em.createNamedQuery("PersonalGym.findByRol").setParameter("rol", role);
            List<StaffEntity> listEntity = q.getResultList();
            ModelMapper modelMapper = new ModelMapper();
            List<Staff> listDomain = new ArrayList<>();
            for (StaffEntity entity : listEntity){
                listDomain.add(new ModelMapper().map(entity, Staff.class));
            }
            return listDomain;
        }
        catch(NoResultException e){
            return null;
        }
    }


}
