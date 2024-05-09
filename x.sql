PGDMP  
                    |         	   shopsmart    16.2    16.2 9               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    26603 	   shopsmart    DATABASE     }   CREATE DATABASE shopsmart WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_Turkey.1254';
    DROP DATABASE shopsmart;
                postgres    false            �            1259    26826    category    TABLE     [   CREATE TABLE public.category (
    id bigint NOT NULL,
    title character varying(255)
);
    DROP TABLE public.category;
       public         heap    postgres    false            �            1259    26832    comment    TABLE     �   CREATE TABLE public.comment (
    id bigint NOT NULL,
    content text,
    created_date timestamp without time zone,
    modified_date timestamp without time zone,
    product_id bigint NOT NULL,
    user_id bigint NOT NULL
);
    DROP TABLE public.comment;
       public         heap    postgres    false            �            1259    26831    comment_id_seq    SEQUENCE     w   CREATE SEQUENCE public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.comment_id_seq;
       public          postgres    false    218                       0    0    comment_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;
          public          postgres    false    217            �            1259    26704    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �            1259    26912    like    TABLE     t   CREATE TABLE public."like" (
    id bigint NOT NULL,
    product_id bigint NOT NULL,
    user_id bigint NOT NULL
);
    DROP TABLE public."like";
       public         heap    postgres    false            �            1259    26944    pending_user    TABLE       CREATE TABLE public.pending_user (
    id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    email character varying(255),
    user_name character varying(255),
    password character varying(255),
    role_name character varying(255)
);
     DROP TABLE public.pending_user;
       public         heap    postgres    false            �            1259    26840    product    TABLE     |  CREATE TABLE public.product (
    id bigint NOT NULL,
    created_date timestamp without time zone,
    description character varying(255),
    img_url character varying(255),
    modified_date timestamp without time zone,
    name character varying(255),
    price bigint,
    stock bigint,
    user_id bigint NOT NULL,
    subcategory_id bigint NOT NULL,
    next_val bigint
);
    DROP TABLE public.product;
       public         heap    postgres    false            �            1259    26941    profile    TABLE     �   CREATE TABLE public.profile (
    id bigint NOT NULL,
    profile_picture character varying(255),
    banner character varying(255),
    about character varying(255),
    user_id bigint NOT NULL,
    rating double precision
);
    DROP TABLE public.profile;
       public         heap    postgres    false            �            1259    26974    refresh_token    TABLE     �   CREATE TABLE public.refresh_token (
    id bigint NOT NULL,
    expiry_date timestamp without time zone NOT NULL,
    token character varying(255) NOT NULL,
    user_id bigint NOT NULL
);
 !   DROP TABLE public.refresh_token;
       public         heap    postgres    false            �            1259    26973    refresh_token_id_seq    SEQUENCE     }   CREATE SEQUENCE public.refresh_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.refresh_token_id_seq;
       public          postgres    false    227                       0    0    refresh_token_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.refresh_token_id_seq OWNED BY public.refresh_token.id;
          public          postgres    false    226            �            1259    26847    role    TABLE     _   CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.role;
       public         heap    postgres    false            �            1259    26852    subcategory    TABLE        CREATE TABLE public.subcategory (
    id bigint NOT NULL,
    title character varying(255),
    category_id bigint NOT NULL
);
    DROP TABLE public.subcategory;
       public         heap    postgres    false            �            1259    26873    user    TABLE       CREATE TABLE public."user" (
    id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    email character varying(255),
    user_name character varying(255),
    password character varying(255),
    role_name character varying(255)
);
    DROP TABLE public."user";
       public         heap    postgres    false            @           2604    26835 
   comment id    DEFAULT     h   ALTER TABLE ONLY public.comment ALTER COLUMN id SET DEFAULT nextval('public.comment_id_seq'::regclass);
 9   ALTER TABLE public.comment ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            A           2604    26977    refresh_token id    DEFAULT     t   ALTER TABLE ONLY public.refresh_token ALTER COLUMN id SET DEFAULT nextval('public.refresh_token_id_seq'::regclass);
 ?   ALTER TABLE public.refresh_token ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    227    227            �          0    26826    category 
   TABLE DATA           -   COPY public.category (id, title) FROM stdin;
    public          postgres    false    216   GB       �          0    26832    comment 
   TABLE DATA           `   COPY public.comment (id, content, created_date, modified_date, product_id, user_id) FROM stdin;
    public          postgres    false    218   �B       �          0    26912    like 
   TABLE DATA           9   COPY public."like" (id, product_id, user_id) FROM stdin;
    public          postgres    false    223   �B       �          0    26944    pending_user 
   TABLE DATA           h   COPY public.pending_user (id, first_name, last_name, email, user_name, password, role_name) FROM stdin;
    public          postgres    false    225    C       �          0    26840    product 
   TABLE DATA           �   COPY public.product (id, created_date, description, img_url, modified_date, name, price, stock, user_id, subcategory_id, next_val) FROM stdin;
    public          postgres    false    219   BC       �          0    26941    profile 
   TABLE DATA           V   COPY public.profile (id, profile_picture, banner, about, user_id, rating) FROM stdin;
    public          postgres    false    224   �D                  0    26974    refresh_token 
   TABLE DATA           H   COPY public.refresh_token (id, expiry_date, token, user_id) FROM stdin;
    public          postgres    false    227   LE       �          0    26847    role 
   TABLE DATA           (   COPY public.role (id, name) FROM stdin;
    public          postgres    false    220   hF       �          0    26852    subcategory 
   TABLE DATA           =   COPY public.subcategory (id, title, category_id) FROM stdin;
    public          postgres    false    221   �F       �          0    26873    user 
   TABLE DATA           b   COPY public."user" (id, first_name, last_name, email, user_name, password, role_name) FROM stdin;
    public          postgres    false    222   H       	           0    0    comment_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.comment_id_seq', 1, false);
          public          postgres    false    217            
           0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 49, true);
          public          postgres    false    215                       0    0    refresh_token_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.refresh_token_id_seq', 9, true);
          public          postgres    false    226            C           2606    26830    category category_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
       public            postgres    false    216            E           2606    26839    comment comment_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.comment DROP CONSTRAINT comment_pkey;
       public            postgres    false    218            R           2606    26916    like like_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."like"
    ADD CONSTRAINT like_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."like" DROP CONSTRAINT like_pkey;
       public            postgres    false    223            V           2606    26950    pending_user pendinguser_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.pending_user
    ADD CONSTRAINT pendinguser_pkey PRIMARY KEY (id);
 G   ALTER TABLE ONLY public.pending_user DROP CONSTRAINT pendinguser_pkey;
       public            postgres    false    225            G           2606    26846    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public            postgres    false    219            T           2606    26960    profile profile_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.profile DROP CONSTRAINT profile_pkey;
       public            postgres    false    224            X           2606    26979     refresh_token refresh_token_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT refresh_token_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.refresh_token DROP CONSTRAINT refresh_token_pkey;
       public            postgres    false    227            I           2606    26851    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    220            N           2606    26856    subcategory subcategory_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.subcategory
    ADD CONSTRAINT subcategory_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.subcategory DROP CONSTRAINT subcategory_pkey;
       public            postgres    false    221            Z           2606    26981 *   refresh_token uk_r4k4edos30bx9neoq81mdvwph 
   CONSTRAINT     f   ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT uk_r4k4edos30bx9neoq81mdvwph UNIQUE (token);
 T   ALTER TABLE ONLY public.refresh_token DROP CONSTRAINT uk_r4k4edos30bx9neoq81mdvwph;
       public            postgres    false    227            K           2606    26967    role uniqueConstraint 
   CONSTRAINT     a   ALTER TABLE ONLY public.role
    ADD CONSTRAINT "uniqueConstraint" UNIQUE (name) INCLUDE (name);
 A   ALTER TABLE ONLY public.role DROP CONSTRAINT "uniqueConstraint";
       public            postgres    false    220            P           2606    26879    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    222            L           1259    26891    fki_fk2    INDEX     F   CREATE INDEX fki_fk2 ON public.subcategory USING btree (category_id);
    DROP INDEX public.fki_fk2;
       public            postgres    false    221            `           2606    26968    user fk    FK CONSTRAINT     �   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fk FOREIGN KEY (role_name) REFERENCES public.role(name) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 3   ALTER TABLE ONLY public."user" DROP CONSTRAINT fk;
       public          postgres    false    220    222    4683            d           2606    26982    refresh_token fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;
 :   ALTER TABLE ONLY public.refresh_token DROP CONSTRAINT fk;
       public          postgres    false    227    222    4688            _           2606    26886    subcategory fk1    FK CONSTRAINT     �   ALTER TABLE ONLY public.subcategory
    ADD CONSTRAINT fk1 FOREIGN KEY (category_id) REFERENCES public.category(id) ON UPDATE CASCADE ON DELETE CASCADE;
 9   ALTER TABLE ONLY public.subcategory DROP CONSTRAINT fk1;
       public          postgres    false    216    221    4675            ]           2606    26892    product fk1    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1 FOREIGN KEY (user_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;
 5   ALTER TABLE ONLY public.product DROP CONSTRAINT fk1;
       public          postgres    false    222    4688    219            [           2606    26902    comment fk1    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fk1 FOREIGN KEY (product_id) REFERENCES public.product(id) ON UPDATE CASCADE ON DELETE CASCADE;
 5   ALTER TABLE ONLY public.comment DROP CONSTRAINT fk1;
       public          postgres    false    219    4679    218            a           2606    26917    like fk1    FK CONSTRAINT     �   ALTER TABLE ONLY public."like"
    ADD CONSTRAINT fk1 FOREIGN KEY (product_id) REFERENCES public.product(id) ON UPDATE CASCADE ON DELETE CASCADE;
 4   ALTER TABLE ONLY public."like" DROP CONSTRAINT fk1;
       public          postgres    false    223    219    4679            c           2606    26961    profile fk1    FK CONSTRAINT     �   ALTER TABLE ONLY public.profile
    ADD CONSTRAINT fk1 FOREIGN KEY (user_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;
 5   ALTER TABLE ONLY public.profile DROP CONSTRAINT fk1;
       public          postgres    false    4688    222    224            ^           2606    26897    product fk2    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk2 FOREIGN KEY (subcategory_id) REFERENCES public.subcategory(id) ON UPDATE CASCADE ON DELETE CASCADE;
 5   ALTER TABLE ONLY public.product DROP CONSTRAINT fk2;
       public          postgres    false    219    221    4686            \           2606    26907    comment fk2    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fk2 FOREIGN KEY (user_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;
 5   ALTER TABLE ONLY public.comment DROP CONSTRAINT fk2;
       public          postgres    false    4688    218    222            b           2606    26922    like fk2    FK CONSTRAINT     �   ALTER TABLE ONLY public."like"
    ADD CONSTRAINT fk2 FOREIGN KEY (user_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;
 4   ALTER TABLE ONLY public."like" DROP CONSTRAINT fk2;
       public          postgres    false    222    223    4688            �   Q   x�3�t�I�.)�����2�.�/�2��ί�M-��p�e�f+�gVf�r�rz'�٘�q�S�Z��TS����� p      �      x������ � �      �   +   x�31�4�44�21�\&� ʄ��D�q�XrY��c���� ��3      �   2   x�32�,*�L�LNrH�M���K���,J�,�4426�tL�������� �{      �   l  x���KN�0E��*�����:B���0AB��LۊM�U���a�S�F��w�;Gf����^e�6�`2���=��|I�t�_t�[Y�j��
�;�i�WN���Q� �-9u�S���؜�v�!��i��!��I�p�)�t�$|)�ĳ� h���!.�ӌ/Q�+rZ�\dlY2Q��
^�Lc�Mc�1@�vYn�-� ���@�YY;D��?$��������
I��V�h��� g1�����d��8L�9�q�U�pe{���ʚ�L Ϯ�P{���\}���5�����8�k��_�~.��	5��*@c�X�b���!_o�}��#-�Ĕ!���m���<u�����l�8�      �   ~   x���;� @�V���`k5����@|4������'��0Y:DHXΰ��3�5No��p��ݷ��O�W!Ҽ��C�P���aJ����Ϋ���ym�z:�>�Vd���G���d���'9��;��            x�]йq%1P���'�)������)9Zm x�.���
��.��+~&�j�ښC4hjK�l�BZ�����<�Q��� ��#(���tRW��W��C�Q;�C��~�zπA�`��|6�����;agy�[9o�P��
*zaF0��nXz�c�*�������q���c��~ߐ-�1��[�#
y�_ >���
�yɹN�j� �'�o��m�v
q�+�t�n�0ZYy���p����y��'��"�~=��o�f�      �   +   x�3�tL����2��M-J�H�+�2�t.-.��M-����� �	�      �   [  x�M��n�0���Sd�X��FhC��Dԩ�QL��I��"�g������ޫ����~�O���!���R��Z����l��$�ځ #a�J�7�y09�����7؇�ԟ2��1���ATN �2jz���Y�B[sTu.>��!1�R�2�q~˷s��-�P�DD�2-uH�J�x(��B��U�����&�q6X]:c|x�:{ cx�.1g֓�F����`����i���e|�љ�C{����\�}k�J�.h�P�"��uk����Q�1����E��郌�?46���:���#=5�<i��g��u�C��T�!�`����L����rX�,�J�����3c�!��      �   R  x�M��v�0���):��mր""��e�I)Dn
D�OՇ��ZZG�k'{}�%T�f��	_2�R�Yg (J2�hIb��=	��e ״Qt;�D��ˊ�pcF��;��=(�,.�h	�8�)a|��6��	�!#5�������xYR��귕wQ�]�-�QBDSr�9�	��5W´y�9j�ae�Ҵ�U��W�U��>P�>Y,�e��&c��C���cc�Й,����a0Uw:l���F-_@'�1����#�RR�S�ޞ��}�sֱ��lL���:�K�'��.}�6�3�ٟ�XE}�H��_/|�T^�E;�� g�+�`��dҪ꼎8�7��Wd���7�����7$�
sM�W������R�N��z-�?�8�>?t�͝�D�mim��<��h12F�y!d��G��[~��a��u�W�1�\9ڵ��7m�~V'��[�+�
�/� ����lF�y��(�T��FoS����u}�cs���v�k����K]�[�8 ���1�A��Y�@k�{��=�̩z\�U>�34�*�Wឌך)���,�Їxeq�7C�]K��^����     