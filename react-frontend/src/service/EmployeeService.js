 import axios from 'axios'

 const EMPLOYEE_SERVICE_BASE_URL = "http://localhost:9191/employee/api/employees/2";
 const EMPLOYEE_ID = 2;
   
 class EmployeeService {

    getEmployee() {
       return axios.get(EMPLOYEE_SERVICE_BASE_URL);
    }
      
 }

 export default new EmployeeService;