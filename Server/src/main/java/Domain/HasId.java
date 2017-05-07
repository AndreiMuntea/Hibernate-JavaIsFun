package Domain;

import java.io.Serializable;

/**
 * Created by andrei on 2017-04-06.
 */
public interface HasId<T extends Serializable> {
    T getId();
    void setId(T id);
}
