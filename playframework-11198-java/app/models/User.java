package models;

import play.mvc.PathBindable;
import play.mvc.QueryStringBindable;

import java.util.Map;
import java.util.Optional;

// #declaration
public class User implements PathBindable<User>, QueryStringBindable<User> {

  public Long id;
  public String name;
  // #declaration

  // #bindPath
  @Override
  public User bindPath(String key, String id) {

    System.out.println("------- key: " + key);
    System.out.println("------- id: " + id);
    System.out.println(this.toString());

    // findById meant to be lightweight operation
    User user = findById(Long.valueOf(id));
    if (user == null) {
      throw new IllegalArgumentException("User with id " + id + " not found");
    }
    return user;
  }

  @Override
  public String unbindPath(String key) {
    return String.valueOf(id);
  }
  // #bindPath

  @Override
  public String javascriptUnbindPath() {
    return "function(k,v) {\n" + "    return v.id;" + "}";
  }

  // stubbed test
  // designed to be lightweight operation
  private User findById(Long id) {
    if (id > 3) return null;
    User user = new User();
    user.id = id;
    user.name = "User " + String.valueOf(id);
    return user;
  }




  public Integer from;
  public Integer to;
  // #declaration

  // #bindQuery
  @Override
  public Optional<User> bindQuery(String key, Map<String, String[]> data) {

    try {
      from = Integer.valueOf(data.get("from")[0]);
      to = Integer.valueOf(data.get("to")[0]);
      return Optional.of(this);

    } catch (Exception e) { // no parameter match return None
      return Optional.empty();
    }
  }

  @Override
  public String unbindQuery(String key) {
    return new StringBuilder().append("from=").append(from).append("&to=").append(to).toString();
  }
  // #bindQuery

  @Override
  public String javascriptUnbindQuery() {
    return new StringBuilder().append("from=").append(from).append(";to=").append(to).toString();
  }
}