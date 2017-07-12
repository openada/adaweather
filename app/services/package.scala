
package object services {

  /**
    * Type the allows to push the database dependencies out of the other services
    */
  type Reader[T] = DataSource => T

}
