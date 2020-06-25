package Code8;

import java.io.Serializable;
import java.util.*;

/**
 * this class has collection treeSet with elements Organization.
 * @see Organization
 */
public class CollectionOrganization implements Serializable {
    private static final long serialVersionUID = 17L;
    private volatile TreeSet<Organization> collection = new TreeSet<>();
    //private static ConcurrentSkipListSet<Organization> CunCollection = new ConcurrentSkipListSet(Comparator.comparing(Organization::getName));
    private static CollectionOrganization collectionOrganization = new CollectionOrganization();
    private Date date;
    private volatile TreeSet<UserInformation> userCollection = new TreeSet<>();

    /**
     * this constructor creat new collection TreeSet. elements are sorted in alphabetical order
     */
    private CollectionOrganization() {
        date = new GregorianCalendar().getTime();
        //cunCollection = new ConcurrentSkipListSet(Comparator.comparing(Organization::getName));
    }

    /**
     * this method adds element to the collection
     * @param or this element will be added to the collection
     */
    public void put(Organization or){
        //cunCollection.add(or);
        collection.add(or);
    }

    /**
     * this method return collection TreeSet
     * @return return collection TreeSet
     */
    public TreeSet<Organization> getCollection() {
        //return cunCollection;
        return collection;
    }

    public TreeSet<UserInformation> getUserCollection() {
        return userCollection;
    }

    /**
     * this method return the minimum element collection TreeSet. Minimum element has the minimum hashCode
     * @return return the minimum element collection TreeSet. if collection is empty, then method returns null
     */
   /* public Organization getMinElement(){
        int min = 0;
        Organization minO = null;
        for (Organization o: CunCollection){
            if(o.hashCode() < min){
                min = o.hashCode();
                minO = o;
            }
        }
        return minO;
    }*/

    /**
     * this method return the maximum element collection TreeSet.Maximum element has thee maximum hashCode
     * @return return the maximum element collection TreeSet. if collection is empty, then method returns null
     */
    /*public Organization getMaxElement(){
        int max = 0;
        Organization maxO = null;
        for (Organization o: CunCollection){
            if(o.hashCode() > max){
                max = o.hashCode();
                maxO = o;
            }
        }
        return maxO;
    }*/

    public void setAllCollection(TreeSet<Organization> list){
        //cunCollection.addAll(list);
        collection.addAll(list);
    }

    public void setUserCollection(TreeSet<UserInformation> list) {
        userCollection.addAll(list);
    }

    public static CollectionOrganization getCollectionOrganization(){
        return collectionOrganization;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        //return "collection = " + cunCollection.first();
        return "collection = " + collection.first();
    }
}
