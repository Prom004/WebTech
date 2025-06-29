package rw.ac.auca.ecommerce.core.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import rw.ac.auca.ecommerce.core.base.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The class AbstractBaseEntity.
 *
 * @author Jeremie Ukundwa Tuyisenge
 * @version 1.0
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractBaseEntity extends AbstractAuditEntity{
    @SuppressWarnings("deprecation")
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "active" , nullable = false)
    private boolean active = Boolean.TRUE;

    public UUID getId() {
        return id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
