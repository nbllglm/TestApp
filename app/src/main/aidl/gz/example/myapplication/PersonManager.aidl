// PersonManager.aidl
package gz.example.myapplication;

// Declare any non-default types here with import statements
import gz.example.myapplication.Person;
interface PersonManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   List<Person> getPersonList();

   void addPerson(in Person person);
}