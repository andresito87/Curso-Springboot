package com.learning.mokito.mokito_demo.service;

import com.learning.mokito.mokito_demo.model.Product;
import com.learning.mokito.mokito_demo.repository.IProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Permite utilizar Mockito para usar mocks en los tests
class ProductServiceTest {

    @Mock // Crea un mock del repositorio, un rpositorio que se comporta como nosotros queremos
    private IProductRepository productRepository;

    @InjectMocks // Inyecta una instancia de productService al que le conecta el mock del repositorio anterior
    private ProductService productService;

    @Test
    void testFindProductByIdReturnsProductWhenFound() {
        // Arrange (Preparación)
        Long productId = 1L;
        Product mockProduct = new Product(productId, "Laptop", 1200.00);
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        // Act (Acción)
        Optional<Product> product = productService.findProductById(productId);

        // Assert (Asegurar)
        assertTrue(product.isPresent());
        assertEquals(mockProduct, product.get());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testFindProductByIdReturnsEmptyOptionalWhenNotFound() {
        // Arrange (Preparación)
        Long productId = 99L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act (Acción)
        Optional<Product> product = productService.findProductById(productId);


        // Assert (Asegurar)
        assertFalse(product.isPresent());
        assertEquals(Optional.empty(), product);
        verify(productRepository, times(1)).findById(productId);

    }

    @Test
    void testGetAllProductsReturnsListOfProducts() {
        // Arrange (Preparación)
        List<Product> mockProducts = Arrays.asList(new Product(1L, "Laptop", 1200.00), new Product(2L, "Mouse", 25.00));
        when(productRepository.findAll()).thenReturn(mockProducts);

        // Act (Acción)
        List<Product> products = productService.getAllProducts();

        // Assert(Asegurar)
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(2, products.size());
        assertEquals("Laptop", products.get(0).getName());
        assertEquals("Mouse", products.get(1).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testSaveProductSavesValidProduct() {
        // Arrange (Preparación)
        Product validProduct = new Product(null, "Teclado", 50.00);
        doNothing().when(productRepository).save(validProduct);

        // Act (Acción)
        productService.saveProduct(validProduct);

        // Assert(Asegurar)
        verify(productRepository, times(1)).save(validProduct);
    }

    @Test
    void testSaveProductThrowsExceptionWhenPriceIsZeroOrLess() {
        // Arrange (Preparación)
        Product invalidProduct = new Product(null, "Auriculares", 0.00);

        // Act (Acción) and Assert (Asegurar)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.saveProduct(invalidProduct));

        // Assert (Asegurar)
        assertEquals("Price must be greater than zero", exception.getMessage());
        verify(productRepository, never()).save(any(Product.class));
    }

}