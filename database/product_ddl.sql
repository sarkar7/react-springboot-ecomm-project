
/*    ----- PRODUCT_CATEGORY Table -----    */
CREATE SEQUENCE product_category_seq start 1 increment 1;

CREATE TABLE product_category (
	id integer NOT NULL DEFAULT nextval('product_category_seq'),
	category_id integer NOT NULL,
	parent_category_id integer NOT NULL,
	category_name varchar2 (50) NOT NULL,
	CONSTRAINT PK_Product_Category PRIMARY KEY (id, category_id, parent_category_id)
);

-- ALTER SEQUENCE product_category_seq OWNED BY product_category.id;


/*    ----- PRODUCT Table -----    */
CREATE SEQUENCE product_seq start 1 increment 1;

CREATE TABLE product (
	product_id integer NOT NULL DEFAULT nextval('product_seq'),
	category_id integer NOT NULL,
	product_name varchar (255),
	description varchar (500),
	CONSTRAINT PK_Product PRIMARY KEY (product_id, product_name)
	CONSTRAINT FK_Product FOREIGN KEY (category_id) REFERENCES product_category(category_id)
);

-- ALTER SEQUENCE product_seq OWNED BY product.product_id;


/*    ----- PRODUCT_ITEM Table -----    */
CREATE SEQUENCE product_item_seq start 1 increment 1;

CREATE TABLE product_item (
	product_item_id integer NOT NULL DEFAULT nextval('product_item_seq'),
	product_id integer NOT NULL,
	sku integer NOT NULL,
	quantity smallint NOT NULL,
	price number(8,2) NOT NULL,
	image_url varchar (500) NOT NULL,
	CONSTRAINT PK_Product_Item PRIMARY KEY (product_item_id, sku)
	CONSTRAINT FK_Product_Item FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- ALTER SEQUENCE product_item_seq OWNED BY product_item.product_item_id;

