package id.jasoet.auth.model

import io.ebean.annotation.WhenCreated
import io.ebean.annotation.WhenModified
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "t_user")
data class User(
        @Id
        var id: Long,
        var name: String,
        var email: String,
        @Column
        var bio: String?,
        @Column
        @NotNull
        var password: String,
        var active: Boolean,
        @ManyToMany
        @field:NotEmpty
        var groups: List<Group>,
        @WhenCreated
        @Column(name = "created")
        var whenCreated: Instant,
        @WhenModified
        @Column(name = "modified")
        var whenModified: Instant
)

@Entity
@Table(name = "t_group")
data class Group(
        @Id
        var id: Long,
        var name: String,
        var policy: String,
        @WhenCreated
        @Column(name = "created")
        var whenCreated: Instant,
        @WhenModified
        @Column(name = "modified")
        var whenModified: Instant
)

