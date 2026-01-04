/** Represents a user in a social network. A user is characterized by a name,
 *  a list of user names that s/he follows, and the list's size. */
 public class User 
{
    // Maximum number of users that a user can follow
    static int maxfCount = 10;

    private String name;       // name of this user
    private String[] follows;  // array of user names that this user follows
    private int fCount;        // actual number of followees (must be <= maxfCount)

    /** Creates a user with an empty list of followees. */
    public User(String name) 
    {
        this.name = name;
        this.follows = new String[maxfCount]; // fixed-size array for storing followees
        this.fCount = 0;                      // initial number of followees
    }

    /** Creates a user with some followees. The only purpose of this constructor is 
     *  to allow testing the toString and follows methods, before implementing other methods. */
    public User(String name, boolean gettingStarted) 
    {
        this(name);
        follows[0] = "Foo";
        follows[1] = "Bar";
        follows[2] = "Baz";
        fCount = 3;
    }

    /** Returns the name of this user. */
    public String getName() 
    {
        return this.name;
    }

    /** Returns the follows array. */
    public String[] getfFollows() 
    {
        return this.follows;
    }

    /** Returns the number of users that this user follows. */
    public int getfCount() 
    {
        return this.fCount;
    }

    /** If this user follows the given name, returns true; otherwise returns false. */
    public boolean follows(String name) 
    {
        for (int i = 0; i < fCount; i++) // for each elemnt in follows check if its the same as name given
        {
            if (this.follows[i].equals(name))
                return true;
        }
        return false;
    }

    /** Makes this user follow the given name. If successful, returns true. 
     *  If this user already follows the given name, or if the follows list is full, does nothing and returns false; */
    public boolean addFollowee(String name) 
    {
        if (follows(name) || this.fCount == 10) // if already in the array or already follows 10 people, return false and dont add
            return false;

        this.follows[fCount] = name;
        fCount++;

        return true;
    }

    /** Removes the given name from the follows list of this user. If successful, returns true.
     *  If the name is not in the list, does nothing and returns false. */
    public boolean removeFollowee(String name) 
    {
        boolean flag = false; // while didnt find the new correct name
        
        for (int i = 0; i < fCount; i++)
        {
            if (this.follows[i].equals(name)) // running till the correct word then changing flag
                flag = true;

            if (flag && i < this.fCount - 1) // if found and index is 2 before the last one
                this.follows[i] = this.follows[i + 1];
        }

        if (flag)
            this.follows[--fCount] = null;  // Removing last elemnt and removing 1 from the count

        return flag; // returning the value if founded or not
    }

    /** Counts the number of users that both this user and the other user follow.
    /*  Notice: This is the size of the intersection of the two follows lists. */
    public int countMutual(User other) 
    {
        int count = 0;
        for (int i = 0; i < this.fCount; i++)
        {
            if (other.follows(this.follows[i]))
                count++;
        }
        
        return count;
    }

    /** Checks is this user is a friend of the other user.
     *  (if two users follow each other, they are said to be "friends.") */
    public boolean isFriendOf(User other) 
    {
        return (this.follows(other.getName()) && other.follows(this.name));
    }

    /** Returns this user's name, and the names that s/he follows. */
    public String toString() 
    {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) 
        {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }
}