/**
 * @see https://github.com/JustBurrow/spring-data-jpa
 */
package kr.lul.spring.data.jpa.common.entity;

import static kr.lul.spring.data.jpa.common.mapping.IdMapping.IdTable.PK;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import kr.lul.data.IntIdDomainObject;
import kr.lul.spring.data.jpa.timestamp.entity.AbstractUpdatable;

/**
 * @author Just Burrow
 * @since 2016. 9. 3.
 */
@MappedSuperclass
public abstract class AbstractIntIdEntity extends AbstractUpdatable implements IntIdDomainObject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = PK, nullable = false, insertable = false, updatable = false)
  private int id;

  /**
   * 구현 클래스의 클래스 정보를 제공한다.
   * 클래스 정보는 {@link #hashCode()}나 {@link #equals(Object)} 메서드에 사용한다.
   *
   * @return
   * @author Just Burrow
   * @since 2016. 9. 3.
   */
  protected abstract Class<? extends AbstractIntIdEntity> implClass();

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // <I>IntIdentifiableDomainObject<ID>
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @author Just Burrow
   * @since 2016. 9. 3.
   */
  @Override
  public int getId() {
    return this.id;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Object
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
   * (non-Javadoc)
   * @author Just Burrow
   * @since 2016. 9. 3.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.implClass(), this.id);
  }

  /*
   * (non-Javadoc)
   * @author Just Burrow
   * @since 2016. 9. 3.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (0 < this.id && null != obj && this.implClass().isAssignableFrom(obj.getClass())) {
      return this.id == ((AbstractIntIdEntity) obj).id;
    } else {
      return false;
    }
  }
}