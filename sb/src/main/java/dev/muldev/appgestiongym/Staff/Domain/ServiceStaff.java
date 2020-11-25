
package dev.muldev.appgestiongym.Staff.Domain;

import java.util.List;

/**
 *
 * @author bunn3
 */
public interface ServiceStaff {
    Staff addStaff(Staff staff);
    Staff getOne(int id);
    Double calculateSalaries();
    List<Staff> listStaff();
    boolean delById(int id);
    Staff getByUsername(String username);
}
