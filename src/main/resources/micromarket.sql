-- ============================================================
-- BASE DE DATOS: MicroMarket
-- ============================================================

CREATE DATABASE IF NOT EXISTS micromarket
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE micromarket;

-- ============================================================
-- MÓDULO I: INVENTARIO Y PRODUCTOS
-- ============================================================

CREATE TABLE categorias (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE productos (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_barras  VARCHAR(50)     NOT NULL UNIQUE,
    nombre         VARCHAR(150)    NOT NULL,
    descripcion    VARCHAR(255),
    precio         DECIMAL(10, 2)  NOT NULL,
    stock          INT             NOT NULL DEFAULT 0,
    activo         BOOLEAN         NOT NULL DEFAULT TRUE,   -- Soft Delete
    categoria_id   BIGINT          NOT NULL,
    CONSTRAINT fk_producto_categoria
        FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

-- ============================================================
-- MÓDULO II: PROVEEDORES Y ABASTECIMIENTO
-- ============================================================

CREATE TABLE proveedores (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    nit      VARCHAR(20)  NOT NULL UNIQUE,
    nombre   VARCHAR(150) NOT NULL,
    telefono VARCHAR(20),
    email    VARCHAR(100),
    direccion VARCHAR(255)
);

-- Relación ManyToMany: Productos <-> Proveedores
CREATE TABLE producto_proveedor (
    producto_id  BIGINT NOT NULL,
    proveedor_id BIGINT NOT NULL,
    PRIMARY KEY (producto_id, proveedor_id),
    CONSTRAINT fk_pp_producto
        FOREIGN KEY (producto_id)  REFERENCES productos(id),
    CONSTRAINT fk_pp_proveedor
        FOREIGN KEY (proveedor_id) REFERENCES proveedores(id)
);

-- ============================================================
-- MÓDULO III: GESTIÓN DE PERSONAL
-- ============================================================

CREATE TABLE empleados (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    cedula          VARCHAR(20)    NOT NULL UNIQUE,
    nombre          VARCHAR(150)   NOT NULL,
    cargo           ENUM('ADMINISTRADOR', 'CAJERO', 'AUXILIAR') NOT NULL,
    fecha_ingreso   DATE           NOT NULL,
    salario         DECIMAL(12, 2) NOT NULL
);

-- ============================================================
-- MÓDULO IV: FACTURACIÓN Y VENTAS
-- ============================================================

CREATE TABLE ventas (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    subtotal     DECIMAL(12, 2) NOT NULL,
    iva          DECIMAL(12, 2) NOT NULL,   -- 19% calculado automáticamente
    total        DECIMAL(12, 2) NOT NULL,
    empleado_id  BIGINT         NOT NULL,
    CONSTRAINT fk_venta_empleado
        FOREIGN KEY (empleado_id) REFERENCES empleados(id)
);

CREATE TABLE detalle_venta (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    venta_id    BIGINT         NOT NULL,
    producto_id BIGINT         NOT NULL,
    cantidad    INT            NOT NULL,
    precio_unit DECIMAL(10, 2) NOT NULL,
    subtotal    DECIMAL(12, 2) NOT NULL,
    CONSTRAINT fk_detalle_venta
        FOREIGN KEY (venta_id)    REFERENCES ventas(id),
    CONSTRAINT fk_detalle_producto
        FOREIGN KEY (producto_id) REFERENCES productos(id)
);